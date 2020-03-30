##  blog项目

## 第一天（复习）
   1、新建project导入前端展示资源（静态页面资源，和样式图片资源;
   2、导入web依赖（RequestMapping一系列注解依赖此jar包）;
      *  <dependency>
      *  <groupId>org.springframework.boot</groupId>
      *  <artifactId>spring-boot-starter-web</artifactId>
      *  </dependency>;
   3、编写第一个demo，添加Controller注解，将此类交给spring管理并声明此类为一个控制层，添git 加
      getMapping（value="url"）声明此url get请求被下方方法管理。
   4、添加thymeleaf依赖，让静态资源正常展示
       <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
       </dependency>
   5、获取并使用路径变量（@PathVariable）和参数变量（@RequestParam）