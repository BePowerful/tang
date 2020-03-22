package com.wcq.tang.bean;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.io.IOException;
import java.util.List;

/**
 * @author wcq
 * @date 2019/12/15 21:59
 */
public class ParticipleUtils {
    public String seeGoodResult(String result){
        CixinTable table = new CixinTable();
        String res="\n"+"[";
        String[] s = result.split(" ");
        for(int i=0;i<s.length;i++){
            String[] split = s[i].split("/");
            String temp = "";
            if(split[1].endsWith(",")){
                temp = split[1].substring(0,split[1].length()-1);
            }else{
                temp = split[1];
            }
            temp = (String) table.getMap().get(temp);
            if(i!=s.length-1){
                s[i]=split[0]+"/"+temp+",";
            }else{
                s[i]=split[0]+"/"+temp;
            }
            res+=s[i];
        }
       return res+"]";
    }
    /**
     * 转为用空格分隔的形式
     * @param result
     * @return
     */
    public String standardResult(String result){
        String res = "";
        String[] s = result.split(" ");
        for(int i=0;i<s.length;i++){
            String[] split = s[i].split("/");
            if(i!=s.length-1){
                res+=split[0]+" ";
            }else{
                res+=split[0];
            }
        }
        return res;
    }
    /**
     * 结巴分词
     * @param content
     * @return
     */
//    public String jieBa(String content){
//        JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
//        List<String> strings = jiebaSegmenter.sentenceProcess(content);
//        StringBuilder data = new StringBuilder();
//        for (int i = 0; i < strings.size(); i++) {
//            if (strings.get(i).equals("，") ||
//                    strings.get(i).equals("。") ||
//                    strings.get(i).equals("、") ||
//                    strings.get(i).equals("：") ||
//                    strings.get(i).equals("？")) {
//                continue;
//            }
//            data.append(strings.get(i)).append(" ");
//        }
//        return data.toString();
//    }
    /**
     * 标准分词
     * @param content
     * @return
     */
    public String standard(String content){
        List<Term> segment = StandardTokenizer.segment(content);
        String s = segment.toString();
        String substring = s.substring(1, s.length() - 1);
        return substring;
    }

    /**
     * NLP分词
     * @param content
     * @return
     */
    public String NLP(String content){
        return NLPTokenizer.analyze(content).toString();
    }

    /**
     * 索引分词
     * @param content
     * @return
     */
    public String indexPar(String content){
        String res = "";
        List<Term> termList = IndexTokenizer.segment(content);
        for (Term term : termList)
        {
            res += term + " [" + term.offset + ":" + (term.offset + term.word.length()) + "]"+"\n";
        }
        return res;
    }

    /**
     * crf分词
     * @param content
     * @return
     */
    public String  crf(String content){
        CRFLexicalAnalyzer analyzer = null;
        try {
            analyzer = new CRFLexicalAnalyzer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return analyzer.analyze(content).toString();
    }

    /**
     * N最短路径分词
     * @param content
     * @return
     */
    public String NShort(String content){
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        String segment = nShortSegment.seg(content).toString();
        String substring = segment.substring(1, segment.length() - 1);
        return substring;
    }

    /**
     * 最短路径分词
     * @param content
     * @return
     */
    public String Short(String content){
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        String segment = shortestSegment.seg(content).toString();
        String substring = segment.substring(1, segment.length() - 1);
        return substring;
    }

    /**
     * 关键词提取
     * @param content
     * @param size
     * @return
     */
    public String extractionKeywords (String content,int size){
        List<String> keywordList = HanLP.extractKeyword(content, size);
        return keywordList.toString();
    }

    /**
     * 自动摘要
     * @param content
     * @param size
     * @return
     */
    public String automaticSummarization(String content,int size){
        List<String> sentenceList = HanLP.extractSummary(content,size);
        return sentenceList.toString();
    }

    /**
     * 短语提取
     * @param content
     * @param size
     * @return
     */
    public String extractPhrase(String content,int size){
        List<String> phraseList = HanLP.extractPhrase(content, size);
        return phraseList.toString();
    }

    /**
     * 依存句法分析
     * @param content
     * @return
     */
    public String analysis(String content){
        String res = null;
        CoNLLSentence sentence = HanLP.parseDependency(content);
        res = sentence.toString()+"\n";
        // 可以方便地遍历它
        for (CoNLLWord word : sentence)
        {
            res += String.format("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
        }
        // 也可以直接拿到数组，任意顺序或逆序遍历
//        CoNLLWord[] wordArray = sentence.getWordArray();
//        for (int i = wordArray.length - 1; i >= 0; i--)
//        {
//            CoNLLWord word = wordArray[i];
//            System.out.printf("%s --(%s)--> %s\n", word.LEMMA, word.DEPREL, word.HEAD.LEMMA);
//        }
        // 还可以直接遍历子树，从某棵子树的某个节点一路遍历到虚根
        CoNLLWord[] wordArray = sentence.getWordArray();
        CoNLLWord head = wordArray[12];
        while ((head = head.HEAD) != null)
        {
            if (head == CoNLLWord.ROOT) System.out.println(head.LEMMA);
            else res+=String.format("%s --(%s)--> ", head.LEMMA, head.DEPREL);
        }
        return res;
    }

    /**
     * 繁体转为简体
     * @param content
     * @return
     */
    public static String ftoJ(String content){
        return HanLP.convertToSimplifiedChinese(content);
    }
}
