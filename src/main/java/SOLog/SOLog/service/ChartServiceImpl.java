package SOLog.SOLog.service;

import SOLog.SOLog.domain.dto.ChartDataResponseDto;
import SOLog.SOLog.domain.dto.TrendMatchChartResponseDto;
import SOLog.SOLog.domain.entity.PriceDiffEntity;
import SOLog.SOLog.domain.entity.StockDataEntity;
import SOLog.SOLog.repository.PriceDiffRepository;
import SOLog.SOLog.repository.StockDataRepository;
import io.swagger.v3.oas.models.links.Link;
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

    public TrendMatchChartResponseDto getTrendMatchStockData(String companyName, Date baseDate, String duration) {


        Long period = 5L;

        /***
         * 기간 1주 ,2주 ,한달 입력기준으로 비교 period 설정
         */
        if (Objects.equals(duration, "one")) {
            period = 5L;
        } else if (Objects.equals(duration, "two")) {
            period = 10L;
        } else {
            period = 20L;
        }
        System.out.println(period);

        //초기화
        Double checkSum = 2.0;

        //기준날짜 등락률데이터, 전체 날짜 등락률 데이터
        List<PriceDiffEntity> data1 = priceDiffRepository.findTopByCompanyAndDateBeforeNative(companyName, baseDate, period);
        List<PriceDiffEntity> data2 = priceDiffRepository.findPriceDiffByCompany_CompanyNameOrderByDateDesc(companyName);//전체 차트데이터
        data1.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));//역순정렬
        data2.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));///역순정렬

        //비슷한 구간 날짜 마킹 리스트
        ArrayList<Date> markingDate = new ArrayList<>();

        // 기준날짜 구간 주가데이터 전 20일 후 5일
        List<StockDataEntity> BaseStockDataEntities = stockDataRepository.findByCompany_CompanyNameAndDurationTypeAndDateBefore(companyName, baseDate);
        BaseStockDataEntities.addAll(stockDataRepository.findByCompany_CompanyNameAndDurationTypeAndDateAfter(companyName, baseDate));

        List<ChartDataResponseDto> BaseChartDataList = new ArrayList<>();
        for (StockDataEntity entity : BaseStockDataEntities) {
            BaseChartDataList.add(new ChartDataResponseDto(
                    entity.getCompany().getCompanyName(),
                    entity.getDate().toString(),
                    entity.getOpenPrice(),
                    entity.getClosePrice(),
                    entity.getHighPrice(),
                    entity.getLowPrice(),
                    entity.getVolume()
            ));
        }
        BaseChartDataList.sort(Comparator.comparing(ChartDataResponseDto::getDate));

        ArrayList<List<ChartDataResponseDto>> similarDataList = new ArrayList<>();

        /**
         * 가장 비슷한 날짜의 squared error 찾기
         */
        for (int i = 0; i < data2.size(); i++) {
            if (data1.get(0).getDate().equals(data2.get(i).getDate()))
                continue;
            double sum = 0;
            if(i+period+1>data2.size())
                break;
            for (int j = 0; j < period; j++) {
                sum += Math.pow(Math.abs(data1.get(j).getDiff() - data2.get(i + j).getDiff()), 2); //mse방식 유사도 검사
            }
            if (sum <= checkSum) {
                checkSum = sum;

            }
        }

        int count=0;
        for (int i = 0; i < data2.size()-period; i++) {
            if (data1.get(0).getDate().equals(data2.get(i).getDate()))
                continue;
            double sum = 0;

            if(count==20)
                break;

            for (int j = 0; j < period; j++) {
                sum += Math.pow(Math.abs(data1.get(j).getDiff() - data2.get(i + j).getDiff()), 2); //mse방식 유사도 검사
            }
            if (sum <= checkSum*1.2) {//squared error에 padding값 1.2배수
                count++;
                Date tempDate = data2.get(i).getDate();
                markingDate.add(tempDate);
                List<StockDataEntity> similarStockDataEntities = stockDataRepository.findByCompany_CompanyNameAndDurationTypeAndDateBefore(companyName, tempDate);
                similarStockDataEntities.addAll(stockDataRepository.findByCompany_CompanyNameAndDurationTypeAndDateAfter(companyName, tempDate));

                List<ChartDataResponseDto> chartDataList2 = new ArrayList<>();
                for (StockDataEntity entity : similarStockDataEntities) {
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
                similarDataList.add(chartDataList2);


            }
        }

        return new TrendMatchChartResponseDto(
                BaseChartDataList,similarDataList,markingDate,baseDate,period
        );

    }

}