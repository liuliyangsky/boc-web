package com.cherrypicks.boc.api.filter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.cherrypicks.boc.api.web.view.SensitiveWordsVO;

@Component
public class WordsFilter {
	
	/*@Autowired
	private TextFilterService textFilterService;*/
	
    private static Log log = LogFactory.getLog(WordsFilter.class);
    public static WordsFilter wmParser;
    private static String CHARSET = "utf-8";
    private boolean initFlag = false;
    //private UnionPatternSet unionPatternSet = new UnionPatternSet();
    private int maxIndex = (int) java.lang.Math.pow(2, 16);
    private int shiftTable[] = new int[maxIndex];
    public Vector<Vector<AtomicPattern>> hashTable = new Vector<Vector<AtomicPattern>>();
    private UnionPatternSet tmpUnionPatternSet = new UnionPatternSet();
    
    protected WordsFilter(){
         
    }
    public WordsFilter getInstance() throws IOException, SQLException{
    	String textFilter="政治";//textFilterService.getTextFilter();
    	//String textFilter=textFilterService.getTextFilter();
    	wmParser = new WordsFilter();
    	if(StringUtils.isBlank(textFilter)){
    		log.debug("Instantiating WmParser is null....");
    		return wmParser;
    	}
    	
    	try {
             log.debug("Instantiating WmParser....");
             String test=new String(textFilter.getBytes(),CHARSET);
             InputStream in = new ByteArrayInputStream(test.getBytes());
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             String line = null;
             while ((line = reader.readLine()) != null) {
            	 wmParser.addFilterKeyWord(new String(line.getBytes(), CHARSET), 1);
             }
             reader.close();
             in.close();
    	 }catch (Exception e) {
              log.error("Exception in Instantiating WordsFilter:" + e);
              e.printStackTrace();
    	 }
        return wmParser;
    }
    
    public boolean addFilterKeyWord(String keyWord, int level) {
        if (initFlag == true)
            return false;
        UnionPattern unionPattern = new UnionPattern();
        Pattern pattern = new Pattern(keyWord);
        AtomicPattern atomicPattern = new AtomicPattern(pattern);
        unionPattern.addNewAtomicPattrn(atomicPattern);
        unionPattern.setLevel(level);
        atomicPattern.setBelongUnionPattern(unionPattern);
        tmpUnionPatternSet.addNewUnionPattrn(unionPattern);
        return true;
    }
 
    public String parse(String content, Vector<String> levelSet){
        try {
            if (initFlag == false)
                init();
            Vector<AtomicPattern> aps = new Vector<AtomicPattern>();
            StringBuilder sb = new StringBuilder();   
            char checkChar;
            for (int i = 0; i < content.length();) {
                checkChar = content.charAt(i);
                if (shiftTable[checkChar] == 0) {
                    Vector<AtomicPattern> tmpAps = new Vector<AtomicPattern>();
                    Vector<AtomicPattern> destAps = hashTable.get(checkChar);
                    int match = 0;
                    for (int j = 0; j < destAps.size(); j++) {
                        AtomicPattern ap = destAps.get(j);
                        if (ap.findMatchInString(content.substring(0, i + 1))){
                            String patternStr = ap.getPattern().getStr();
                            if (match > 0){
                                sb.setLength(sb.length() - patternStr.length());
                            } else {
                                sb.setLength(sb.length() - patternStr.length() + 1);
                            }
                            appendStar(patternStr, sb);
                            tmpAps.add(ap);
                            match++;
                        }
                    }
                    aps.addAll(tmpAps);
                    if (tmpAps.size() <= 0){
                        sb.append(checkChar);
                    }
                    i++;
                } else {
                    if (i + shiftTable[checkChar] <= content.length()){
                        sb.append(content.substring(i, i + shiftTable[checkChar]));
                    } else {
                        sb.append(content.substring(i));
                    }
                    i = i + shiftTable[checkChar];
                }
            }
            parseAtomicPatternSet(aps, levelSet);
            return sb.toString();
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
        }
        return "";
    }
     
    private static void appendStar(String patternStr, StringBuilder sb){
        for (int c = 0;c < patternStr.length(); c++){
            char ch = patternStr.charAt(c);
            //判斷是否是中文
            if ((ch >= 0x4e00 && ch <= 0x9FA5) || (ch >= 0xF900 && ch <= 0xFA2D)){
                sb.append("＊");
            } else {
                sb.append("*");
            }
        }
    }
 
 
    private void parseAtomicPatternSet(Vector<AtomicPattern> aps,
            Vector<String> levelSet) {
        while (aps.size() > 0) {
            AtomicPattern ap = aps.get(0);
            UnionPattern up = ap.belongUnionPattern;
            if (up.isIncludeAllAp(aps)) {
                //levelSet.add(new Integer(up.getLevel()));
            	levelSet.add(up.getSet().get(0).getPattern().getStr());
            }
            aps.remove(0);
        }
    }
 
    // shift table and hash table of initialize
    private void init() {
        initFlag = true;
        for (int i = 0; i < maxIndex; i++)
        	hashTable.add(new Vector<AtomicPattern>());
        shiftTableInit();
        hashTableInit();
    }
 
    public void clear() {
        tmpUnionPatternSet.clear();
        initFlag = false;
    }
 
    private void shiftTableInit() {
        for (int i = 0; i < maxIndex; i++)
            shiftTable[i] = 2;
        Vector<UnionPattern> upSet = tmpUnionPatternSet.getSet();
        for (int i = 0; i < upSet.size(); i++) {
            Vector<AtomicPattern> apSet = upSet.get(i).getSet();
            for (int j = 0; j < apSet.size(); j++) {
                AtomicPattern ap = apSet.get(j);
                Pattern pattern = ap.getPattern();
                if (shiftTable[pattern.charAtEnd(1)] != 0)
                    shiftTable[pattern.charAtEnd(1)] = 1;
                if (shiftTable[pattern.charAtEnd(0)] != 0)
                    shiftTable[pattern.charAtEnd(0)] = 0;
            }
        }
    }
 
    private void hashTableInit() {
        Vector<UnionPattern> upSet = tmpUnionPatternSet.getSet();
        for (int i = 0; i < upSet.size(); i++) {
            Vector<AtomicPattern> apSet = upSet.get(i).getSet();
            for (int j = 0; j < apSet.size(); j++) {
                AtomicPattern ap = apSet.get(j);
                Pattern pattern = ap.getPattern();
                if (pattern.charAtEnd(0) != 0) {
                    //hashTable[pattern.charAtEnd(0)].add(ap);
                	hashTable.get(pattern.charAtEnd(0)).add(ap);
                }
            }
        }
    }
    
    /**
     * 提供給 外部調用
     * @param content
     * @return
     * @throws UnsupportedEncodingException
     */
    public static SensitiveWordsVO filterString(String content) throws UnsupportedEncodingException{
    	if(StringUtils.isBlank(content)){
    		return null;
    	}
    	Vector<String> levelSet = new Vector<String>();
    	SensitiveWordsVO vo=new SensitiveWordsVO();
    	vo.setFilterText(wmParser.parse(new String(content.getBytes(), CHARSET), levelSet));
    	Set<String> sensitiveWords=new HashSet<String>();
    	for (String string1 : levelSet) {
    		sensitiveWords.add(string1);
		}
    	vo.setSensitiveWords(sensitiveWords);
    	return vo;
    }
    
    //test function
    public static void main(String args[]) {
        try {
        	
        	WordsFilter filterEngine = new WordsFilter();//WordsFilter.getInstance();

            Vector<String> levelSet = new Vector<String>();
            String str = "单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功" +
            		"单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功" +
            		"单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功" +
            		"单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动和强奸和shit法!轮功法*轮*功";
            SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss.SSS");
            System.out.println("文本长度：" + str.length());
            System.out.println("敏感词汇总数:" + filterEngine.getInstance().tmpUnionPatternSet.getSet().size());
            Date start = new Date(System.currentTimeMillis());
            System.out.println("过滤开始：" + sf.format(start));
             
            System.out.println(str);
            System.out.println(filterEngine.getInstance().parse(new String(str.getBytes(), CHARSET), levelSet));
             
            Date end = new Date(System.currentTimeMillis());
            System.out.println("过滤完毕：" + sf.format(end));
            System.out.println("文本中出现敏感词个数：" + levelSet.size());
            System.out.println("耗时：" + (end.getTime() - start.getTime()) + "ms");
            
            System.out.println("过滤开始：" + sf.format(start));
            
            System.out.println("过convertString开始：" + filterString("单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动").getFilterText());
            System.out.println("过convertString开始：" + filterString("单个的政治，政治运动和强奸和shit法!轮功法*轮*功单个的政治，政治运动").getSensitiveWords());
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
         
    }
}
 
class Pattern { // string
    Pattern(String str) {
        this.str = str;
    }
 
    public char charAtEnd(int index) {
        if (str.length() > index) {
            return str.charAt(str.length() - index - 1);
        } else
            return 0;
    }
 
    public String str;
 
    public String getStr() {
        return str;
    };
    
}
 
class AtomicPattern {
//    public boolean findMatchInString(String str) throws Exception {
//        String patStr = new String(this.pattern.str.getBytes("ISO-8859-1"), "UTF-8");
//        str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
//        if (patStr.length() > str.length())
//            return false;
//        int beginIndex = str.lastIndexOf(patStr.charAt(0) + "");
//        if (beginIndex != -1){
//            String eqaulLengthStr = str.substring(beginIndex);
//            if (patStr.equalsIgnoreCase(eqaulLengthStr))
//                return true;
//        }
//        return false;
//    }
    public boolean findMatchInString(String str) {
        if (this.pattern.str.length() > str.length())
            return false;
        int beginIndex = str.lastIndexOf(this.pattern.getStr().charAt(0) + "");
        if (beginIndex != -1){
            String eqaulLengthStr = str.substring(beginIndex);
            if (this.pattern.str.equalsIgnoreCase(preConvert(eqaulLengthStr)))
                return true;
        }
        return false;
    }
    private String preConvert(String content) {
        String retStr = new String();
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            //if (this.isValidChar(ch)) {
                retStr = retStr + ch;
            //}
        }
        return retStr;
    }
    /** 驗證是否是字符 **/
    @SuppressWarnings("unused")
	private boolean isValidChar(char ch) {
        if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z')
                || (ch >= 'a' && ch <= 'z'))
            return true;
        if ((ch >= 0x4e00 && ch <= 0x9FA5) || (ch >= 0xF900 && ch <= 0xFA2D))
            return true;
        return false;
    }
 
    AtomicPattern(Pattern pattern) {
        this.pattern = pattern;
    };
 
    private Pattern pattern;
    public UnionPattern belongUnionPattern;
 
    public UnionPattern getBelongUnionPattern() {
        return belongUnionPattern;
    }
 
    public void setBelongUnionPattern(UnionPattern belongUnionPattern) {
        this.belongUnionPattern = belongUnionPattern;
    }
 
    public Pattern getPattern() {
        return pattern;
    }
 
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}
 
class SameAtomicPatternSet {
    SameAtomicPatternSet() {
        SAPS = new Vector<AtomicPattern>();
    };
 
    public Vector<AtomicPattern> SAPS;
}
 
class UnionPattern { // union string
    UnionPattern() {
        this.apSet = new Vector<AtomicPattern>();
    }
 
    public Vector<AtomicPattern> apSet;
 
    public void addNewAtomicPattrn(AtomicPattern ap) {
        this.apSet.add(ap);
    }
 
    public Vector<AtomicPattern> getSet() {
        return apSet;
    }
 
    public boolean isIncludeAllAp(Vector<AtomicPattern> inAps) {
        if (apSet.size() > inAps.size())
            return false;
        for (int i = 0; i < apSet.size(); i++) {
            AtomicPattern ap = apSet.get(i);
            if (isInAps(ap, inAps) == false)
                return false;
        }
        return true;
    }
 
    private boolean isInAps(AtomicPattern ap, Vector<AtomicPattern> inAps) {
        for (int i = 0; i < inAps.size(); i++) {
            AtomicPattern destAp = inAps.get(i);
            if (ap.getPattern().getStr().equalsIgnoreCase(destAp.getPattern().getStr()))
               return true;
        }
        return false;
    }
 
    public void setLevel(int level) {
        this.level = level;
    }
 
    public int getLevel() {
        return this.level;
    }
 
    private int level;
}
 
class UnionPatternSet { // union string set
    UnionPatternSet() {
        this.unionPatternSet = new Vector<UnionPattern>();
    }
 
    public void addNewUnionPattrn(UnionPattern up) {
        this.unionPatternSet.add(up);
    }
 
    public Vector<UnionPattern> unionPatternSet;
 
    public Vector<UnionPattern> getSet() {
        return unionPatternSet;
    }
 
    public void clear() {
        unionPatternSet.clear();
    }
}