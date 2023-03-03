
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author macd
 * @version 2.0
 * @since 2.0
 */
public class SteamUtils {

    /**
     * 快捷的stream map 方法
     * @param data
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> map(List<T> data, Function<T,R> function){
        if (data.isEmpty()) {
            return Collections.emptyList();
        }
        return data.parallelStream().map(function).collect(Collectors.toList());
    }

    /**
     * map func
     * @param data
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> Stream<R> mapFunc(List<T> data, Function<T,R> function){
        if (data.isEmpty()) {
            return Stream.empty();
        }
        return data.parallelStream().map(function);
    }

    /**
     * 快捷的stream map  distinct
     * @param data
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> List<R> mapDistinct(List<T> data, Function<T,R> function){
        if (data.isEmpty()) {
            return Collections.emptyList();
        }
        return data.parallelStream().map(function).distinct().collect(Collectors.toList());
    }

    /**
     * 过滤
     * @param data
     * @param function
     * @param <T>
     * @return
     */
    public static <T> List<T> filter(List<T> data, Predicate<T> function){
        if (data.isEmpty()) {
            return Collections.emptyList();
        }
        return data.parallelStream().filter(function).collect(Collectors.toList());
    }

    /**
     * 将list转换为Map
     * @param data
     * @param fun1
     * @param fun2
     * @param <T>
     * @param <R>
     * @param <I>
     * @return
     */
    public static <T,R,I> Map<R,I> listToMap(List<T> data, Function<T,R> fun1, Function<T,I> fun2){
        return data.parallelStream().collect(Collectors.toMap(fun1,fun2,(k1,k2)->k2));
    }

    /**
     * 将list转为group的map
     * @param data
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T,R> Map<R,List<T>> listToGroup(List<T> data,Function<T,R> function){
        if (data.isEmpty()) {
            return new HashMap<>();
        }
        return data.parallelStream().collect(Collectors.groupingBy(function));
    }

    /**
     * 统计
     * @param data
     * @param function
     * @param <T>
     * @return
     */
    public static <T> Integer sum(List<T> data, ToIntFunction<T> function) {
        return data.parallelStream().collect(Collectors.summingInt(function));
    }
}

