package io.jaeyeon.fastcampusboard.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * <form> 데이터 전달 테스트용 인코더 추가
 * form data 는 api 와는 다른 형태로 직렬화하여 사용해야 한다.
 * 객체를 규약에 맞게 form data 포맷의 문자열로 바꿔주는
 * 유틸리티를 테스트 전용으로 만들고, 테스트도 함께 작성
 * 이게 없으면 `post().param()` 형태로 값을 넣을 수도 있지만
 * 표현이 실제 post request 전송과 꼭 맞지 않는 듯 하고,
 * 객체가 아니라 파라미터 단위로 넣어줘야 하므로 불편함.
 */
@TestComponent
public class FormDataEncoder {
    
    private final ObjectMapper mapper;

    public FormDataEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String encode(Object obj) {
        Map<String, String> fieldMap = mapper.convertValue(obj, new TypeReference<>() {});
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.setAll(fieldMap);

        return UriComponentsBuilder.newInstance()
                .queryParams(valueMap)
                .encode()
                .build()
                .getQuery();
    }
}
