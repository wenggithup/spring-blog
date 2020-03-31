###  blog项目

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
## 第二天（复习）
    1、建立db，用于数据交互（数据库weng，创建user（用户表），blog（博客表），comment（评论表）
    2、新建bean实体对象，对应数据库中的字段
    3、新建config目录下，新建mybatis.xml文件，插入驼峰插件
        <settings>
          <setting name="mapUnderscoreToCamelCase" value="true"/>
        </settings>
    4、在pom文件中添加mysql、mybaits驱动jar包
    <!--mybatis依赖-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>
    <!--mysql驱动依赖-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
    5、在properties文件中添加扫描文件，扫描dao层下所有的文件
        mybatis.mapper-locations=classpath:dao/*.xml
    6、dao层定义对应方法，mybatis负责实现，通过用户名/blogid调用对应方法，由mybatis封装返回结果对象
       ，在控制层将对应的前台展示需要的字段放入modle域中
