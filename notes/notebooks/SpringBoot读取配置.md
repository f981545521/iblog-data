
### 读取配置

#### 在application.properties写入下面代码
```
    test.boolean=true
    test.string=abc
    test.integer=123
    test.long=123
    test.float=1.2345678123456
    test.double=1.2345678123456
    test.array=1,3,4,5,6,1,2,3
    test.list=1,3,4,5,6,1,2,3
    test.set=1,3,4,5,6,1,2,3
    test.map={name:"张三", age:18}
```

#### Java中
```
    @Value("${test.boolean}")
    private Boolean testBoolean;
 
    @Value("${test.string}")
    private String testString;
 
    @Value("${test.integer}")
    private Integer testInteger;
 
    @Value("${test.long}")
    private Long testLong;
 
    @Value("${test.float}")
    private Float testFloat;
 
    @Value("${test.double}")
    private Double testDouble;
 
    @Value("#{'${test.array}'.split(',')}")
    private String[] testArray;
 
    @Value("#{'${test.list}'.split(',')}")
    private List<String> testList;
 
    @Value("#{'${test.set}'.split(',')}")
    private Set<String> testSet;
    
    @Value("#{${test.map}}")
    private Map<String, Object> testMap;

```
#### Java中为static属性赋值
```
    public static String UPLOAD_MODE = null;

    @Value("${upload.mode}")
    public void setUploadMode(String uploadMode) {
        UPLOAD_MODE = uploadMode;
    }
```








