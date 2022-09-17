package io.github.dunwu.springboot.web;

import io.github.dunwu.springboot.dto.BaseResponse;
import io.github.dunwu.springboot.dto.InfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
public class JsonController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/exception")
    @SuppressWarnings(value = "all")
    public BaseResponse exception() {
        throw new AppException("一个未知错误");
    }

    @RequestMapping("/exception2")
    public BaseResponse<Integer> exception2() {
        int num = 10 / 0;
        return BaseResponse.success(num);
    }

    @RequestMapping("/exception3")
    public ResponseEntity<String> exception3() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(name = "/postInfo", method = RequestMethod.POST)
    public void postInfo(@RequestBody InfoDTO infoDTO) {
        log.info("infoDTO = [{}]", infoDTO);
    }

    @RequestMapping("/success")
    public BaseResponse<InfoDTO> success() {
        InfoDTO systemInfoDTO = new InfoDTO();
        systemInfoDTO.setAppName("JSON测试应用");
        systemInfoDTO.setVersion("1.0.0");
        return BaseResponse.success(systemInfoDTO);
    }

    @RequestMapping(name = "/getInfo", method = RequestMethod.GET)
    public InfoDTO getInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        InfoDTO infoDTO = null;
        try {
            infoDTO = new InfoDTO("JSON测试应用", "1.0.0", sdf.parse("2019-01-01 12:00:00"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return infoDTO;
    }

}
