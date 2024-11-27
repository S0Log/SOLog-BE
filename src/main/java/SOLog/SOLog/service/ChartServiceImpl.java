package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.ChartDataResponseDto;
import SOLog.SOLog.domain.entity.PriceDiffEntity;
import SOLog.SOLog.domain.entity.StockDataEntity;
import SOLog.SOLog.repository.PriceDiffRepository;
import SOLog.SOLog.repository.StockDataRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Collection;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {
    private final StockDataRepository stockDataRepository;
    private final PriceDiffRepository priceDiffRepository;

    @Override
    public List<ChartDataResponseDto> getCompanyStockData(String companyName, String durationType) {
        // companyName과 durationType을 기준으로 데이터를 조회
        List<StockDataEntity> stockDataEntities = stockDataRepository.findByCompany_CompanyNameAndDurationType(companyName, durationType);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 조회된 데이터를 ChartDataResponseDto로 변환
        return stockDataEntities.stream()
                .sorted(Comparator.comparing(StockDataEntity::getDate))
                .map(stockDataEntity -> new ChartDataResponseDto(
                        stockDataEntity.getCompany().getCompanyName(),
                        dateFormat.format(stockDataEntity.getDate()),
                        stockDataEntity.getOpenPrice(),
                        stockDataEntity.getClosePrice(),
                        stockDataEntity.getHighPrice(),
                        stockDataEntity.getLowPrice(),
                        stockDataEntity.getVolume()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<ChartDataResponseDto> getCompanyStockDataSetStart(String companyName, String durationType) {

        Date startDate = new Date(117, 8, 1);

        // companyName과 durationType을 기준으로 데이터를 조회
        List<StockDataEntity> stockDataEntities = stockDataRepository.findByCompany_CompanyNameAndDurationType(companyName, durationType);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        return stockDataEntities.stream()
                .filter(stockDataEntity -> stockDataEntity.getDate().after(startDate))
                .sorted(Comparator.comparing(StockDataEntity::getDate))
                .map(stockDataEntity -> new ChartDataResponseDto(
                        stockDataEntity.getCompany().getCompanyName(),
                        dateFormat.format(stockDataEntity.getDate()),
                        stockDataEntity.getOpenPrice(),
                        stockDataEntity.getClosePrice(),
                        stockDataEntity.getHighPrice(),
                        stockDataEntity.getLowPrice(),
                        stockDataEntity.getVolume()
                ))
                .collect(Collectors.toList());
    }
    public List<ChartDataResponseDto>[] getTrendMatchStockData(String companyName, Date date1,Date date2, String duration) {
        List<PriceDiffEntity> data1 = priceDiffRepository.findPriceDiffByConditions(companyName,date1);// 기준 날짜
        List<PriceDiffEntity> data2 = priceDiffRepository.findPriceDiffByConditions(companyName,date2);// 찾고 싶은 이후 날짜
        data1.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));//역순정렬
        data2.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));///역순정렬

        int period = 5;

        if(Objects.equals(duration, "one")){
            period = 5;
        }
        else if(Objects.equals(duration, "two")){
            period = 10;
        }
        else{
            period = 20;
        }
        Date markingdate = null;
        for(int i=0;i<data2.size();i++) {
            if(data1.get(0).getDate().equals(data2.get(i).getDate()))
                continue;
            double sum = 0;
            for (int j = 0; j < period; j++) {
                sum+=Math.pow(Math.abs(data1.get(j).getDiff()-data2.get(i+j).getDiff()),2); //mse방식 유사도 검사
            }
            if(sum<2){//값 조정 필요
                markingdate = data2.get(i).getDate();
                break;
            }
        }


        List<StockDataEntity> stockDataEntities1 = stockDataRepository.findByCompany_CompanyNameAndDurationTypeAndDateBefore(companyName,date1);
        stockDataEntities1.addAll(stockDataRepository.findByCompany_CompanyNameAndDurationTypeAndDateAfter(companyName,  date1));

        List<ChartDataResponseDto> chartDataList1 = new ArrayList<>();
        for (StockDataEntity entity : stockDataEntities1) {
            chartDataList1.add(new ChartDataResponseDto(
                    entity.getCompany().getCompanyName(),
                    entity.getDate().toString(),
                    entity.getOpenPrice(),
                    entity.getClosePrice(),
                    entity.getHighPrice(),
                    entity.getLowPrice(),
                    entity.getVolume()
            ));
        }
        chartDataList1.sort(Comparator.comparing(ChartDataResponseDto::getDate));


        List<ChartDataResponseDto> chartDataList2 = new ArrayList<>();

        if(markingdate!=null) {


            List<StockDataEntity> stockDataEntities2 = stockDataRepository.findByCompany_CompanyNameAndDurationTypeAndDateBefore(companyName,  markingdate);
            stockDataEntities2.addAll(stockDataRepository.findByCompany_CompanyNameAndDurationTypeAndDateAfter(companyName,markingdate));

            for (StockDataEntity entity : stockDataEntities2) {
                chartDataList2.add(new ChartDataResponseDto(
                        entity.getCompany().getCompanyName(),
                        entity.getDate().toString(),
                        entity.getOpenPrice(),
                        entity.getClosePrice(),
                        entity.getHighPrice(),
                        entity.getLowPrice(),
                        entity.getVolume()
                ));
            }
            chartDataList2.sort(Comparator.comparing(ChartDataResponseDto::getDate));
        }

        return new List[]{chartDataList1, chartDataList2};



    }

}