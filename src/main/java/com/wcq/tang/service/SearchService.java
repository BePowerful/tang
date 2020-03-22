package com.wcq.tang.service;

import com.github.pagehelper.Page;
import com.wcq.tang.dto.CorpusDto;
import com.wcq.tang.dto.SeaOriDto;
import com.wcq.tang.dto.SearchCorpusDto;
import com.wcq.tang.model.Corpus;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Threecup;
import org.springframework.cache.annotation.Cacheable;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:14
 */
public interface SearchService {
    List<CorpusDto> searchCorpus(String content);

    List<CorpusDto> searchOriginal(String content);
    //
    CorpusDto previewCorpus(Long corpusId);

    CorpusDto previewOriginal(Long originalId);

    String getContent(CorpusDto dto);

    void downloadFile(HttpServletResponse response, Long originalId);

    @Cacheable(cacheNames = "dsd",key = "#root.methodName")
    List<SearchCorpusDto> getDefaultSearchCorpusDto();

    List<SearchCorpusDto> searchCorpusByParam(String param,Integer model);

    SearchCorpusDto searchCorpusById(Long corpusId);

    @Cacheable(cacheNames = "dsd",key = "#root.methodName")
    List<Threecup> getDefaultSearchThree();

    List<Threecup> searchThree(String param, Integer condition);

    @Cacheable(cacheNames = "dsd",key="#root.methodName")
    List<SeaOriDto> getDefaultSearchOriginal();

    List<SeaOriDto> originalToSeaOriDto(List<Original> originalList,String param);

    List<SeaOriDto> searchOriginalByParam(String param, Integer condition);

    String originalYulan(Long originalId);

    int deleteOriginalById(Long originalId);


    int deleteCorpusById(Long corpusId);

    @Cacheable(cacheNames = "cora",key = "#page")
    Page<Corpus> getAllCorpus(Integer page,Integer limit);

    @Cacheable(cacheNames = "thra",key = "#page")
    Page<Threecup> getAllThree(int page, int limit);

    @Cacheable(cacheNames = "oria",key = "#page+#format")
    Page<Original> getAllOriginal(int page, int limit, String format);
}
