package com.wcq.tang.service.user;

import com.github.pagehelper.Page;
import com.wcq.tang.model.Corpus;
import com.wcq.tang.model.Original;
import com.wcq.tang.model.Threecup;
import com.wcq.tang.model.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author wcq
 * @version 1.0
 * @date 2020/3/3 16:15
 */
public interface UserHomeService {
    @Cacheable(cacheNames = "coru",key = "#root.methodName+'['+#user.userId+#page+#limit+']'")
    Page<Corpus> getUserCorpus(User user, Integer page, Integer limit);
    @Cacheable(value = "oriu",key = "#root.methodName+'['+#user.userId+#page+#limit+']'")
    Page<Original> getUserOriginals(User user, Integer page, Integer limit);
    @Cacheable(value = "thru",key = "#root.methodName+'['+#user.userId+#page+#limit+']'")
    Page<Threecup> getUserThreecups(User user,Integer page,Integer limit);
}
