package org.example.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author huang
 */
@FeignClient(value = "module-01", contextId = "Module01EchoApi", path = "/module01")
public interface Module01EchoApi {
    /**
     * echo
     *
     * @param str 字符串
     * @return 返回值
     */
    @GetMapping("/echo/{str}")
    String echo(@PathVariable(value = "str") String str);
}
