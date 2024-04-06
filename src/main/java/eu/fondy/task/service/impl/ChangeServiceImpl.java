package eu.fondy.task.service.impl;


import eu.fondy.task.dto.ChangeRequestDto;
import eu.fondy.task.dto.ChangeResponseDto;
import eu.fondy.task.entity.ChangeResult;
import eu.fondy.task.repository.ChangeResultRepository;
import eu.fondy.task.service.ChangeService;
import eu.fondy.task.utils.ObjectConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChangeServiceImpl implements ChangeService {

    private final ChangeResultRepository repository;


    @Override
    public Optional<ChangeResult> getChange(UUID id) {
        return repository.findById(id);
    }

    @Override
    public ChangeResponseDto calculate(ChangeRequestDto requestDto) {

//        CoinPenceBreakdown coinPenceBreakdown = new CoinPenceBreakdown();
//        coinPenceBreakdown.setFifty(2);
//        coinPenceBreakdown.setOne(3);
//
//
//        CoinPoundsBreakdown coinPoundsBreakdown = new CoinPoundsBreakdown();
//        coinPoundsBreakdown.setTwo(22);
//        coinPoundsBreakdown.setTen(220);

        ChangeResult changeResult = new ChangeResult();
        changeResult.setExternalID(requestDto.getId());
        changeResult.setPenceSubmitted(requestDto.getPence());
        changeResult.setPenceBreakdown(Map.of(50, 3,
                10, 0,
                15, 2));
        changeResult.setPoundsBreakdown(Map.of(50, 4,
                10, 1,
                15, 0));

        ChangeResult stored = repository.save(changeResult);
        return ObjectConverter.toDto(stored);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }


//    private Map<Integer, Integer> calcOptimalChange(SortedMap<Integer, Integer> pence) {
//        SortedMap<Integer, Integer> sortedMap = new TreeMap<>(Comparator.reverseOrder());
//        sortedMap.putAll(Map.of(
//                50, 50000,
//                    20, 2000
//        ));
//
//
//    }


    //public static int calc(int bn, int[] denoms) {}
//    int[] coinArr = new int[n + 1];
//    Arrays.fill(coinArr, Integer.MAX_VALUE);
//    coinArr[0] = 0;
//    int minAmount = 0;
//
//    for (int denom : denoms) {
//        for (int i = 1; i < coinArr.length; i++) {
//            if (denom <= i) {
//                if (coinArr[i - denom] == Integer.MAX_VALUE) {
//                    minAmount = Integer.MAX_VALUE;
//                } else {
//                    minAmount = coinArr[i - denom] + 1;
//                }
//                coinArr[i] = Math.min(coinArr[i], minAmount);
//            }
//        }
//    }
//
//    return coinArr[n] == Integer.MAX_VALUE ? -1 : coinArr[n];
//}

}
