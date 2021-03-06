# Spring Cloud学习

罗洪佳 JAVA-AI _2017.11.08_    

>[Spring-Cloud中文文档](https://demo.cc/spring-cloud-dalston.html)

## 0. 为什么要用Spring Cloud？

在微服务架构中，当一个大型系统被拆分成微服务系统以后，不仅包括功能拆分，还包括系统拆分、代码拆分、数据库拆分、缓存拆分等，多个系统的部署、维护、调用关系、调度、监控、fail over就会成为一系列问题。同时微服务系统划分越多，调用链路可能会越长，调用链监控、全链路trace也会成为问题。
而spring cloud 就是这样诞生的，它为服务治理而生。

### 0.1 单体应用

最开始工作的时候，我们的输出是一个大WAR包，采用SSI框架（SPRING+STRUTS+IBATIS）。所有的页面（JSP），控制层，服务全都打在一个包里。如果说有点服务的概念也是webservice这样的。 然后，我们的系统内部针对不同的业务进行了代码层级的拆分，比如订单，商品，客户这样的层级。
大体上，是这样的：

![](pic/0.1.png)

### 0.2 服务化架构

再后面，随着大后台，小前台概念的提出，要求做到大后台一次构建长久使用，前台随时改版，保持活力。我们开始尝试当时比较流行的DUBBO来拆分服务。并且对服务涉及的资源（数据库，内存等等）也进行了拆分。
他大概是这样的：

![](pic/0.2.png)

### 0.3 微服务

实际上服务化架构已经能够满足大部分的需求了，那我们为什么要用微服务呢？ 从网上摘抄了几点：

* 微服务架构强调业务系统需要彻底的组件化和服务化，一个组件就是一个产品，可以独立对外提供服务
* 微服务不再强调传统SOA架构里面比较重的ESB企业服务总线
* 微服务强调每个微服务都有自己独立的运行空间，包括数据库资源。
* 微服务架构本身来源于互联网的思路，因此组件对外发布的服务强调了采用HTTP Rest API的方式来进行
* 微服务的切分粒度会更小

### 0.4 为什么选Spring Cloud

我们之前使用dubbo服务，也是微服务的一种解决方案，选Spring Cloud就在于他们的区别：

* Spring Cloud的应用一个组件就是一个产品，可以独立对外提供服务。而dubbo很难直接对外提供服务，相互依赖严重，也有暴露自己后台架构的风险。
* Spring Cloud的应用对外发布的服务采用HTTP Rest API的方式来进行。而dubbo采用RPC框架，虽然也可以支持HTTP，但实际生产中使用更多的是dubbo协议，这种协议并不是通用的，不利于扩展。
* Spring Cloud是一个大框架，大体系。相对更加稳定成熟，各种配套齐全。而dubbo主要就是一个服务发现注册的组件。缺乏其他必要的支持。
* Spirng Cloud天然支持Spring Boot，更加便于业务落地。

## 1. 怎么用Spring Cloud？

![](pic/0.png)

### 1.1 Spring Cloud集成说明

_这些项目是Spring Cloud官方项目或是对Spring Cloud进行了有益的补充以及基于Spring Cloud最佳实践_

- Spring Cloud启动(Spring Cloud Starters) 

    Spring Boot式的启动项目，为Spring Cloud提供开箱即用的依赖管理。
- 服务网关(Zuul)

    Zuul 是在云平台上提供动态路由,监控,弹性,安全等边缘服务的框架。Zuul 相当于是设备和 Netflix 流应用的 Web 网站后端所有请求的前门。
- 消息总线(Spring Cloud Bus)

    事件、消息总线，用于在集群（例如，配置变化事件）中传播状态变化，可与Spring Cloud Config联合实现热部署。
- 服务配置(Spring Cloud Config)

    配置管理工具包，让你可以把配置放到远程服务器，集中化管理集群配置，目前支持本地存储、Git以及Subversion。
- 服务治理(Eureka)

    云端服务发现，一个基于**REST**的服务，用于定位服务，以实现云端中间层服务发现和故障转移。
- 服务治理(Spring Cloud Zookeeper)

    操作Zookeeper的工具包，用于使用zookeeper方式的服务发现和配置管理。
- 服务容错(Hystrix)

    熔断器，容错管理工具，旨在通过熔断机制控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。
- 配置管理API(Archaius)

    配置管理API，包含一系列配置管理API，提供动态类型化属性、线程安全配置操作、轮询框架、回调机制等功能。
- 服务配置服务跟踪(Sleuth)

    日志收集工具包，封装了Dapper和log-based追踪以及Zipkin和HTrace操作，为SpringCloud应用实现了一种分布式追踪解决方案。
- 大数据操作(Spring Cloud Data Flow)

    大数据操作工具，作为Spring XD的替代产品，它是一个混合计算模型，结合了流数据与批量数据的处理方式。
- 服务安全(Spring Cloud Security)

    基于spring security的安全工具包，为你的应用程序添加安全控制。
- 数据流操作(Spring Cloud Stream)

    数据流操作开发包，封装了与Redis,Rabbit、Kafka等发送接收消息。
- 客户端负载均衡(Ribbon)

    提供云端负载均衡，有多种负载均衡策略可供选择，可配合服务发现和断路器使用。
- 云端任务调度(Spring Cloud Task)

    提供云端计划任务管理、任务调度。
- 声明式REST调用(Feign)

    Feign是一种声明式、模板化的HTTP客户端。

### 1.2. Spring Cloud特性

- Spring Cloud专注于提供良好的开箱即用经验的典型用例和可扩展性机制覆盖。

- 分布式/版本化配置

- 服务注册和发现

- 路由

- service - to - service调用

- 负载均衡

- 断路器

- 分布式消息传递

### 1.3. 示例

<pre>
本示例主要涉及到：Spring boot, Eureka, Ribbon, Feign, Hystrix, Zuul
</pre>

| 模块名称 | 模块说明 | 访问地址 |
| ------------- |:-------------:| -----:|
|demo-consumer-h5|用户服务调用者,采用原始的RestTemplate调用|	http://127.0.0.1:9991/api/hi/习大大|
|demo-consumer-h5-ribbon-hystrix|用户服务调用者，采用ribbon做客户端负载均衡|	http://127.0.0.1:9992/api/hi/习大大ribbon|
|demo-consumer-h5-feign|feign声明式服务调用者|	http://127.0.0.1:9993/api/hi/习大大feign|
|demo-provider-service|	服务提供者|	http://127.0.0.1:9995/service/hi/习大大 http://127.0.0.1:9996/service/hi/彭麻麻|
|demo-eureka-server|分布式服务注册中心(单点)	|http://127.0.0.1:9999|
|demo-eureka-server-high|分布式服务注册中心(高可用版本)|	http://127.0.0.1:9998 http://127.0.0.1:9997|
|demo-zuul-server|网关服务|	http://127.0.0.1:5555/api-a/api/hi/习大大
|demo-config-server|配置服务（待定）|	http://127.0.0.1:7777/config

#### 1.3.1 demo-provider-service 服务提供者

先看服务接口，和 **dubbo** 一样

<pre>
<code>
@Service
public class UserServiceImpl implements UserService {
    @Override
    public String sayHi(String name) {
        return name + ", Hi";
    }
}
</code>
</pre>

不一样的是，spring cloud并不是从这儿暴露出去的，而是暴露的http接口，因此还需要使用controller包装
<pre>
<code>
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/service/hi/{name}")
    public String getHi(@PathVariable String name){
        return userService.sayHi(name);
    }
}
</code>
</pre>

到此，一个简单的服务提供者就搭建好了。而需要的pom只有

<pre>
<code>
    &lt;dependency&gt;
        &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
        &lt;artifactId&gt;spring-boot-starter-web&lt;/artifactId&gt;
    &lt;/dependency&gt;
</code>
</pre>

最后实现一个启动类

<pre>
<code>
    @SpringBootApplication
    public class DemoServiceMasterApplication {
        public static void main(String[] args) {
            SpringApplication.run(DemoServiceMasterApplication.class, args);
        }
    }
</code>
</pre>

#### 1.3.2 demo-consumer-h5 服务消费者

和dubbo不一样的是，spring cloud的微服务是http接口的，所以可以直接使用我们平时调用http的方法去消费。

并且，Spring Cloud提供了 **RestTemplate** 用来调用http接口。

* 首先创建MVCConfiguration

<pre>
<code>
@Configuration
@EnableWebMvc
public class MVCConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
        RequestMappingHandlerAdapter requestMappingHandlerAdapter=new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> messageConverters =new ArrayList<>();
        messageConverters.add(mappingJackson2HttpMessageConverter());
        requestMappingHandlerAdapter.setMessageConverters(messageConverters);
        return requestMappingHandlerAdapter;
    }

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMediaTypes =new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        return  mappingJackson2HttpMessageConverter;
    }

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate =new RestTemplate();
        return restTemplate;
    }
}
</code>
</pre>

* 然后创建Controller

<pre>
<code>
@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api/hi/{name}")
    public String get(@PathVariable String name){
        return this.restTemplate.getForObject("http://127.0.0.1:9996/service/hi/{1}",String.class, name);
    }
}
</code>
</pre>

消费者直连生产者测试

![消费者直连生产者](pic/1.png)

#### 1.3.3 使用Eureka实现服务发现组件

很明显，直连的方式在真正的项目落地的时候不可取。因此需要一个服务发现和注册的组件，Spring Cloud提供了Eureka。

搭建一个简单的Eureka注册中心非常简单，首先实现一个启动类。

这儿使用@EnableEurekaServer注解声明这是一个Eureka Server
<pre>
<code>
@EnableEurekaServer
@SpringBootApplication
public class DemoEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaServerApplication.class, args);
    }
}
</code>
</pre>

然后在application.properties配置一下

<pre>
<code>
# 应用名称，每个项目不同，同样的名字在eureka里面代表同一个服务。
spring.application.name=demo-eureka-server
#服务启动端口
server.port=9999
#eureka服务的主机地址
eureka.instance.hostname=127.0.0.1
#定义注册中心的地址
eureka.client.serviceUrl.defaultZone=http://${eureka.instance.hostname}:${server.port}/eureka/
#使用ip地址的形式定义注册中心的地址
eureka.instance.prefer-ip-address=true
#关闭自我保护
eureka.server.enable-self-preservation=false
</code>
</pre>

然后启动Eureka服务，通过http://127.0.0.1:9999访问

![Eureka界面](pic/2.png)

#### 1.3.4 服务注册

搭建好Eureka注册中心后，就可以将刚才的service注册到上面。

步骤同样很简单
* pom里面添加

<pre>
<code>
    &lt;dependency>
        &lt;groupId>org.springframework.cloud&lt;/groupId>
        &lt;artifactId>spring-cloud-starter-eureka&lt;/artifactId>
    &lt;/dependency>
</code>
</pre>

* application.properties里面添加

<pre>
<code>
    #####Eureka Client Config#######
    #设置服务名称，注册到eureka里面会使用这个名字
    spring.application.name=demo-provider-service
    #eureka 单实例配置
    eureka.client.service-url.defaultZone=http://127.0.0.1:9999/eureka
    #将自己的IP注册到Eureka Server
    eureka.instance.prefer-ip-address=true
</code>
</pre>

* 启动类添加@EnableDiscoveryClient注解

<pre>
<code>
    @EnableDiscoveryClient
    @SpringBootApplication
    public class DemoServiceMasterApplication {
        public static void main(String[] args) {
            SpringApplication.run(DemoServiceMasterApplication.class, args);
        }
    }
</code>
</pre>

运行 DemoEurekaServerApplication 启动Eureka服务，然后运行 DemoServiceMasterApplication，进入Eureka界面查看。

![服务注册](pic/3.png)

#### 1.3.5 Eureka Server 高可用实现

我们使用dubbo注册服务的时候，会同时启用多个zk，形成集群，保证服务注册中心非单点。使用Eureka也是这样的。

首先，同时启动多个Eureka服务，并且让他们相互注册。测试环境我们使用多个spring boot的多环境配置来启动不同的Eureka.

在 **demo-eureka-server-high** 工程中准备了master，slave两个Eureka服务。

* 首先，准备三个配置文件：

application.properties

<pre>
<code>
    #设置服务名称，注册到eureka里面会使用这个名字
    spring.application.name=demo-eureka-server-high
    ##使用ip地址的形式定义注册中心的地址
    eureka.instance.prefer-ip-address=true
</code>
</pre> 
application-master.properties 

<pre>
<code>
    server.port=9997
    eureka.instance.hostname1=127.0.0.1
    eureka.instance.hostname2=127.0.0.1
    eureka.client.serviceUrl.defaultZone=http://demo:demo123@${eureka.instance.hostname1}:9997/eureka/,http://demo:demo123@${eureka.instance.hostname2}:9998/eureka/
</code>
</pre> 

application-slave.properties。

<pre>
<code>
    server.port=9998
    eureka.instance.hostname1=127.0.0.1
    eureka.instance.hostname2=127.0.0.1
    eureka.client.serviceUrl.defaultZone=http://demo:demo123@${eureka.instance.hostname1}:9997/eureka/,http://demo:demo123@${eureka.instance.hostname2}:9998/eureka/
</code>
</pre> 

* 然后，准备两个启动类：

<pre>
<code>
@EnableEurekaServer
@SpringBootApplication
public class DemoEurekaMasterServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaMasterServerApplication.class, args);
    }
}
</code>
</pre>

<pre>
<code>
@EnableEurekaServer
@SpringBootApplication
public class DemoEurekaSlaveServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaSlaveServerApplication.class, args);
    }
}
</code>
</pre>  

* 最后，设置启动参数：

![](pic/4.png)

启动两个Eureka服务，进入界面查看

![](pic/5.png)

修改服务提供者的eureka地址就可以连接到新的注册中心
<pre>
<code>
#高可用改造后可以加上多个注册中心的地址
eureka.client.service-url.defaultZone=http://127.0.0.1:9998/eureka/,http://127.0.0.1:9997/eureka/
</code>
</pre>

启动provider后，查看Eureka注册中心。

![](pic/6.png)

#### 1.3.6 Eureka Server实现安全验证

默认情况下Eureka Server允许匿名访问，这里创建一个需要登录后才能访问Eureka Server

* 首先，pom.xml中添加安全依赖

<pre>
<code>
    &lt;dependency>
        &lt;groupId>org.springframework.boot&lt;/groupId>
        &lt;artifactId>spring-boot-starter-security&lt;/artifactId>
    &lt;/dependency>
</code>
</pre>

* 然后在application.properties中添加http basic验证配置

<pre>
<code>
    ##eureka server 启用安全验证
    security.user.name=demo
    security.user.password=demo123
    security.basic.enabled=true
</code>
</pre>

重启Eureka服务，重新进入界面，就需要用户密码认证了

![](pic/7.png)

而服务提供者只需要更改配置：eureka.client.serviceUrl.defaultZone

<pre>
<code>
http://user:password@127.0.0.1:9997/eureka/,http://user:password@127.0.0.1:9998/eureka/
</code>
</pre>

#### 1.3.7 使用Ribbon实现客户端负载均衡

> Ribbon是Netflix发布的负载均衡器,有助于控制HTTP和TCP客户端的行为，为Ribbon配置服务提供者地址列表后，Ribbon可以基于负载均衡算法(例如轮询、随机)自动的帮助消费者去请求。 
在SpringCloud中,当Ribbon与Eureka配合使用时，Ribbon可自动从Eureka Server获取服务提供者地址列表，并基于负载均衡算法，请求其中一个服务提供者实例。

* 修改服务消费端pom

<pre>
<code>
    &lt;dependency>
        &lt;groupId>org.springframework.cloud&lt;/groupId>
        &lt;artifactId>spring-cloud-starter-ribbon&lt;/artifactId>
    &lt;/dependency>
    
    &lt;dependency>
        &lt;groupId>org.springframework.cloud&lt;/groupId>
        &lt;artifactId>spring-cloud-starter-hystrix&lt;/artifactId>
    &lt;/dependency>
    
    &lt;dependency>
        &lt;groupId>com.fasterxml.jackson.core&lt;/groupId>
        &lt;artifactId>jackson-databind&lt;/artifactId>
        &lt;version>2.8.8&lt;/version>
    &lt;/dependency>
</code>
</pre>

* 实现 MVCConfiguration 

同 **3.2** 章节，只是RestTemplate上添加@LoadBalanced注解即可整合Ribbon实现客户端的负载均衡。

<pre>
<code>
@Bean
@LoadBalanced //开启客户端负载均衡
public RestTemplate restTemplate(){
    return new RestTemplate();
}
</code>
</pre>

* Controller层

<pre>
<code>
@RestController
public class UserController {
    private static String API_URL = "http://DEMO-PROVIDER-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api/hi/{name}")
    public String hi(@PathVariable String name){
        ResponseEntity<String> responseEntity =this.restTemplate.getForEntity(
                API_URL + "/service/hi/{1}",String.class,name );
        return responseEntity.getBody();
    }
}
</code>
</pre>

同之前的Controller对比，请求地址变更为http://DEMO-PROVIDER-SERVICE,而DEMO-PROVIDER-SERVICE是微服务的虚拟主机名，当Ribbon和Eureka配合使用时，会自动将虚拟主机名映射成微服务的网络地址。

* 启动调试

![](pic/8.png)

打印 responseEntity.getHeaders().get("X-Application-Context");

![](pic/9.png)

由上图可知，已实现负载均衡。

#### 1.3.8 使用Feign实现声明式REST调用

> Feign是Netflix公司开发的声明式、模板化的HTTP客户端，其主要用途是更加便捷、优雅的调用HTTP API,Spring Cloud在原有基础上使Feign支持SpringMVC注解，并且整合了Ribbon和Eureka。

之前的maven模块demo-consumer-h5-ribbon-hystrix是使用RestTemplate调用RESTful API。

<pre>
ResponseEntity<String> responseEntity =this.restTemplate.getForEntity(
        API_URL + "/service/hi/{1}",String.class,name );
</pre>

这种方式，通过拼接字符串构造URL，如果是订单提交这类数据较多的服务，用字符串拼接虽然可以达到目的，但是维护比较困难。而引入Netflix公司开发的Feign实现声明式的RESTful API调用，可以完美解决这个问题。

* POM文件

<pre>
<code>
    &lt;dependency>
        &lt;groupId>org.springframework.cloud&lt;/groupId>
        &lt;artifactId>spring-cloud-starter-feign&lt;/artifactId>
    &lt;/dependency>
</code>
</pre>

* 创建一个Feign接口

<pre>
<code>
// demo-provider-service是客户端名称，用于创建Ribbon负载均衡器。
@FeignClient(value = "DEMO-PROVIDER-SERVICE")
public interface UserFeignClient {
    // 这儿的value-"name"不能少，否则报错Caused by: java.lang.IllegalStateException: PathVariable annotation was empty on param 0.
    @RequestMapping("/service/hi/{name}")
    String hi(@PathVariable(value = "name") String name);
}
</code>
</pre>

* 修改Controller，调用Feign接口

<pre>
<code>
@RestController
public class UserFeignController {
    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/api/hi/{name}")
    public String hi(@PathVariable  String name){
        return userFeignClient.hi(name);
    }
}
</code>
</pre>

* 实现启动类

<pre>
<code>
@EnableFeignClients //开启SpringCloud Feign的支持功能
@EnableDiscoveryClient
@SpringBootApplication
public class DemoFeignH5Application {
    public static void main(String[] args){
        SpringApplication.run(DemoFeignH5Application.class, args);
    }
}
</code>
</pre>

启动

![](pic/10.png)

调用服务两次，两台服务器各相应一次，说明实现负载均衡

![](pic/11.png)

![](pic/12.png)

#### 1.3.9 使用Hystrix实现微服务的容错处理

> 熔断器，容错管理工具，旨在通过熔断机制控制服务和第三方库的节点,从而对延迟和故障提供更强大的容错能力。

* POM

<pre>
<code>
    &lt;dependency>
        &lt;groupId>org.springframework.cloud&lt;/groupId>
        &lt;artifactId>spring-cloud-starter-hystrix&lt;/artifactId>
    &lt;/dependency>
</code>
</pre>

* 启动类

添加@EnableCircuitBreaker为项目启动断路器支持
<pre>
<code>
@EnableCircuitBreaker //启动断路器支持
@EnableDiscoveryClient
@SpringBootApplication
public class DemoH5RibbonHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoH5RibbonHystrixApplication.class, args);
    }
}
</code>
</pre>

* 修改Controller

<pre>
<code>
@RestController
public class UserController {
    private static String API_URL = "http://DEMO-PROVIDER-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiFallback", commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "1000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000") },
            threadPoolProperties = { @HystrixProperty(name = "coreSize",value = "1"), @HystrixProperty(name="maxQueueSize",value = "20") } )
    @GetMapping("/api/hi/{name}")
    public String hi(@PathVariable String name){
        ResponseEntity<String> responseEntity =this.restTemplate.getForEntity(
                API_URL + "/service/hi/{1}",String.class,name );
        System.out.println(responseEntity.getHeaders().get("X-Application-Context"));
        return responseEntity.getBody();
    }

    /**
     * 该方法与hi方法具有相同的参数和返回值类型。
     * @return
     */
    public String hiFallback(String name){
        return "fallback:" + name;// 降级内容
    }
}
</code>
</pre>

由代码可知，为hi方法添加一个回退方法hiFallback，在hi方法上，使用注解@HystrixCommand的fallBackMethod属性，指定回退方法是hiFallback。

* 启动服务

并且关掉提供者，返回如下结果

![](pic/13.png)

#### 1.3.10 使用Zuul实现统一网关

> Zuul是Netflix出品的一个基于JVM路由和服务端的负载均衡器。

首先还是看怎么实现：

* POM

<pre>
<code>
    &lt;dependency>
        &lt;groupId>org.springframework.cloud&lt;/groupId>
        &lt;artifactId>spring-cloud-starter-zuul&lt;/artifactId>
    &lt;/dependency>
</code>
</pre>

* 启动类

添加@EnableZuulProxy建立一个网关服务。 
     
<pre>
<code>
@EnableZuulProxy
@SpringCloudApplication 
public class DemoZuulServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoZuulServerApplication.class, args);
    }
}
</code>
</pre>

* 启动服务

![](pic/15.png)

通过网关调用服务

![](pic/16.png)

## 2. 为什么这么用？

### 2.1 Eureka服务发现与注册的机制

#### 2.1.1 Eureka客户端

先看看我们怎么用Eureka的？

<pre>
<code>
@EnableDiscoveryClient
@SpringBootApplication
public class DemoServiceMasterApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoServiceMasterApplication.class, args);
    }
}
</code>
</pre>

很明显，@EnableDiscoveryClient 声明了这是一个Eureka客户端，自然从这儿开始。先看源码：

<pre>
<code>
/**
 * Annotation to enable a DiscoveryClient implementation.
 * @author Spencer Gibb
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(EnableDiscoveryClientImportSelector.class)
public @interface EnableDiscoveryClient {
}
</code>
</pre>
源码说的很明白：Annotation to enable a DiscoveryClient implementation。注解将开启一个DiscoveryClient实例。
那就接着看DiscoveryClient，至于怎么开启的，猜测是AOP生成一个DiscoveryClient。

DiscoveryClient是一个接口，CTRL+T（eclipse）看看他的关系：

![](pic/17.png)

嗯，接着看EurekaDiscoveryClient。它持有EurekaClient实例。而EurekaClient是com.netflix包里面的接口，具体的注册逻辑应该就在里面了。看看它的结构

![](pic/18.png)

有两个实现，一个是CloudEurekaClient（SpringCloud包），一个是DiscoveryClient（Netflix包）。查看源码后得知，CloudEurekaClient继承了DiscoveryClient。
并且调用了DiscoveryClient的构造函数
<pre>
<code>
public class CloudEurekaClient extends DiscoveryClient {
    private static final Log log = LogFactory.getLog(CloudEurekaClient.class);
    private final AtomicLong cacheRefreshedCount;
    private ApplicationContext context;

    public CloudEurekaClient(ApplicationInfoManager applicationInfoManager, EurekaClientConfig config, ApplicationContext context) {
        this(applicationInfoManager, config, (DiscoveryClientOptionalArgs)null, context);
    }

    public CloudEurekaClient(ApplicationInfoManager applicationInfoManager, EurekaClientConfig config, DiscoveryClientOptionalArgs args, ApplicationContext context) {
        super(applicationInfoManager, config, args);// 这儿实例化了一个Eureka的DiscoveryClient
        this.cacheRefreshedCount = new AtomicLong(0L);
        this.context = context;
    }

    protected void onCacheRefreshed() {
        if (this.cacheRefreshedCount != null) {
            long newCount = this.cacheRefreshedCount.incrementAndGet();
            log.trace("onCacheRefreshed called with count: " + newCount);
            this.context.publishEvent(new HeartbeatEvent(this, newCount));
        }

    }
}
</code>
</pre>

到此基本进入了服务注册的核心了。先把之前的几个类和接口的关系梳理下：

![](pic/19.png)

然后，接着看DiscoveryClient，从刚才调用的构造方法开始：

<pre>
<code>
    DiscoveryClient(ApplicationInfoManager applicationInfoManager, EurekaClientConfig config, DiscoveryClient.DiscoveryClientOptionalArgs args, Provider<BackupRegistry> backupRegistryProvider) {
        this.RECONCILE_HASH_CODES_MISMATCH = Monitors.newCounter("DiscoveryClient_ReconcileHashCodeMismatch");
        this.FETCH_REGISTRY_TIMER = Monitors.newTimer("DiscoveryClient_FetchRegistry");
        
        ...// 不展现了 太多了
        
        // 这儿判断了是否Eureka注册
        if (!config.shouldRegisterWithEureka() && !config.shouldFetchRegistry()) {
        
            ...// 不展现了 太多了
            
        } else {
            try {
                this.scheduler = Executors.newScheduledThreadPool(3, (new ThreadFactoryBuilder()).setNameFormat("DiscoveryClient-%d").setDaemon(true).build());
                this.heartbeatExecutor = new ThreadPoolExecutor(1, this.clientConfig.getHeartbeatExecutorThreadPoolSize(), 0L, TimeUnit.SECONDS, new SynchronousQueue(), (new ThreadFactoryBuilder()).setNameFormat("DiscoveryClient-HeartbeatExecutor-%d").setDaemon(true).build());
                this.cacheRefreshExecutor = new ThreadPoolExecutor(1, this.clientConfig.getCacheRefreshExecutorThreadPoolSize(), 0L, TimeUnit.SECONDS, new SynchronousQueue(), (new ThreadFactoryBuilder()).setNameFormat("DiscoveryClient-CacheRefreshExecutor-%d").setDaemon(true).build());
                this.eurekaTransport = new DiscoveryClient.EurekaTransport(null);
                this.scheduleServerEndpointTask(this.eurekaTransport, args);
                Object azToRegionMapper;
                if (this.clientConfig.shouldUseDnsForFetchingServiceUrls()) {
                    azToRegionMapper = new DNSBasedAzToRegionMapper(this.clientConfig);
                } else {
                    azToRegionMapper = new PropertyBasedAzToRegionMapper(this.clientConfig);
                }

                if (null != this.remoteRegionsToFetch.get()) {
                    ((AzToRegionMapper)azToRegionMapper).setRegionsToFetch(((String)this.remoteRegionsToFetch.get()).split(","));
                }

                this.instanceRegionChecker = new InstanceRegionChecker((AzToRegionMapper)azToRegionMapper, this.clientConfig.getRegion());
            } catch (Throwable var8) {
                throw new RuntimeException("Failed to initialize DiscoveryClient!", var8);
            }

            if (this.clientConfig.shouldFetchRegistry() && !this.fetchRegistry(false)) {
                this.fetchRegistryFromBackup();
            }
            // 真正的注册入口
            this.initScheduledTasks();

            try {
                Monitors.registerObject(this);
            } catch (Throwable var7) {
                logger.warn("Cannot register timers", var7);
            }

            DiscoveryManager.getInstance().setDiscoveryClient(this);
            DiscoveryManager.getInstance().setEurekaClientConfig(config);
            this.initTimestampMs = System.currentTimeMillis();
            logger.info("Discovery Client initialized at timestamp {} with initial instances count: {}", this.initTimestampMs, this.getApplications().size());
        }
    }
</code>
</pre>

查看initScheduledTasks方法，从名字就能看出来，这是启动了一些task。

<pre>
<code>
    private void initScheduledTasks() {
        // 设置间隔时间等
        int renewalIntervalInSecs;
        int expBackOffBound;
        if (this.clientConfig.shouldFetchRegistry()) {
            renewalIntervalInSecs = this.clientConfig.getRegistryFetchIntervalSeconds();
            expBackOffBound = this.clientConfig.getCacheRefreshExecutorExponentialBackOffBound();
            // 服务刷新
            this.scheduler.schedule(new TimedSupervisorTask("cacheRefresh", this.scheduler, this.cacheRefreshExecutor, renewalIntervalInSecs, TimeUnit.SECONDS, expBackOffBound, new DiscoveryClient.CacheRefreshThread()), (long)renewalIntervalInSecs, TimeUnit.SECONDS);
        }
        
        // 如果使用Eureka注册
        if (this.clientConfig.shouldRegisterWithEureka()) {
            renewalIntervalInSecs = this.instanceInfo.getLeaseInfo().getRenewalIntervalInSecs();
            expBackOffBound = this.clientConfig.getHeartbeatExecutorExponentialBackOffBound();
            logger.info("Starting heartbeat executor: renew interval is: " + renewalIntervalInSecs);
            // 心跳（续约）
            this.scheduler.schedule(new TimedSupervisorTask("heartbeat", this.scheduler, this.heartbeatExecutor, renewalIntervalInSecs, TimeUnit.SECONDS, expBackOffBound, new DiscoveryClient.HeartbeatThread(null)), (long)renewalIntervalInSecs, TimeUnit.SECONDS);
            // 注册实例
            this.instanceInfoReplicator = new InstanceInfoReplicator(this, this.instanceInfo, this.clientConfig.getInstanceInfoReplicationIntervalSeconds(), 2);
            // 状态改变的监听
            this.statusChangeListener = new StatusChangeListener() {
                public String getId() {
                    return "statusChangeListener";
                }

                public void notify(StatusChangeEvent statusChangeEvent) {
                    if (InstanceStatus.DOWN != statusChangeEvent.getStatus() && InstanceStatus.DOWN != statusChangeEvent.getPreviousStatus()) {
                        DiscoveryClient.logger.info("Saw local status change event {}", statusChangeEvent);
                    } else {
                        DiscoveryClient.logger.warn("Saw local status change event {}", statusChangeEvent);
                    }

                    DiscoveryClient.this.instanceInfoReplicator.onDemandUpdate();
                }
            };
            if (this.clientConfig.shouldOnDemandUpdateStatusChange()) {
                // 注册中心状态变更监听委托给applicationInfoManager
                this.applicationInfoManager.registerStatusChangeListener(this.statusChangeListener);
            }
            // 启动注册
            this.instanceInfoReplicator.start(this.clientConfig.getInitialInstanceInfoReplicationIntervalSeconds());
        } else {
            logger.info("Not registering with Eureka server per configuration");
        }

    }
</code>
</pre>

initScheduledTasks启动了4种任务：

* 服务刷新
* 心跳（续约）
* 启动注册
* 状态改变的监听

我们先讲服务注册，InstanceInfoReplicator是注册实例的入口，它实现了Runnable，查看InstanceInfoReplicator的start方法

<pre>
<code>
    public void start(int initialDelayMs) {
        if (this.started.compareAndSet(false, true)) {
            this.instanceInfo.setIsDirty();
            // 延迟启动注册。
            Future next = this.scheduler.schedule(this, (long)initialDelayMs, TimeUnit.SECONDS);
            this.scheduledPeriodicRef.set(next);
        }
    }
</code>
</pre>

InstanceInfoReplicator的实例通过ScheduledExecutorService启动，查看它实现的Runnable的run方法

<pre>
<code>
    public void run() {
        try {
            discoveryClient.refreshInstanceInfo();
            Long dirtyTimestamp = instanceInfo.isDirtyWithTime();
            if (dirtyTimestamp != null) {
                discoveryClient.register();
                instanceInfo.unsetIsDirty(dirtyTimestamp);
            }
        } catch (Throwable t) {
            logger.warn("There was a problem with the instance info replicator", t);
        } finally {
            Future next = scheduler.schedule(this, replicationIntervalSeconds, TimeUnit.SECONDS);
            scheduledPeriodicRef.set(next);
        }
    }
</code>
</pre>

很明显 this.discoveryClient.register();这是注册。继续看它里面的逻辑

<pre>
<code>
    boolean register() throws Throwable {
        logger.info(PREFIX + appPathIdentifier + ": registering service...");
        EurekaHttpResponse<Void> httpResponse;
        try {
            httpResponse = eurekaTransport.registrationClient.register(instanceInfo);
        } catch (Exception e) {
            logger.warn("{} - registration failed {}", PREFIX + appPathIdentifier, e.getMessage(), e);
            throw e;
        }
        if (logger.isInfoEnabled()) {
            logger.info("{} - registration status: {}", PREFIX + appPathIdentifier, httpResponse.getStatusCode());
        }
        return httpResponse.getStatusCode() == 204;
    }
</code>
</pre>

相信看到这儿，大家已经都懂了。最后的注册其实就是通过Eureka封装的Http方法去通知Eureka服务。

那服务刷新怎么实现的呢？ 回到上面讲 initScheduledTasks 启动了4种任务，其中，服务刷新代码如下：

<pre>
<code>
// 定时器定时执行服务刷新
this.scheduler.schedule(new TimedSupervisorTask("cacheRefresh", this.scheduler, this.cacheRefreshExecutor, renewalIntervalInSecs, TimeUnit.SECONDS, expBackOffBound, new DiscoveryClient.CacheRefreshThread()), (long)renewalIntervalInSecs, TimeUnit.SECONDS);
</code>
</pre>

刷新服务的任务：new DiscoveryClient.CacheRefreshThread() 调用内部类实例化了一个TASK

<pre>
<code>
    class CacheRefreshThread implements Runnable {
        CacheRefreshThread() {
        }

        public void run() {
            DiscoveryClient.this.refreshRegistry();
        }
    }
</code>
</pre>

<pre>
<code>
    @VisibleForTesting
    void refreshRegistry() {
        try {
            boolean isFetchingRemoteRegionRegistries = this.isFetchingRemoteRegionRegistries();
            boolean remoteRegionsModified = false;
            String latestRemoteRegions = this.clientConfig.fetchRegistryForRemoteRegions();
            ... // Region Zone的处理
            
            // 刷新服务
            boolean success = this.fetchRegistry(remoteRegionsModified);
            if (success) {
                this.registrySize = ((Applications)this.localRegionApps.get()).size();
                this.lastSuccessfulRegistryFetchTimestamp = System.currentTimeMillis();
            }

            if (logger.isDebugEnabled()) {
                ...// 略                
            }
        } catch (Throwable var9) {
            logger.error("Cannot fetch registry from server", var9);
        }

    }
</code>
</pre>

在boolean success = this.fetchRegistry(remoteRegionsModified);里面也是通过HTTP去请求服务端的一个接口，实现服务刷新。同样的心跳服务（服务续约）也是一样，就不再展开。

#### 2.1.2 Eureka服务端

同样的，我们先还是从使用注解开始

<pre>
<code>
@EnableEurekaServer
@SpringBootApplication
public class DemoEurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoEurekaServerApplication.class, args);
    }
}
</code>
</pre>

注解EnableEurekaServer开启了一个Eureka服务，通过引用的pom可以知道，调用了spring-cloud-starter-eureka。最终实现eureka的是eureka-core包。
在此包下面可以找到一个EurekaBootStrap，这个名字一看就知道是开始的入口。我就是这样找的。进入此类发现它是基于servlet的。
<pre>
<code>
public class EurekaBootStrap implements ServletContextListener {
    ...// 略
    
    public void contextInitialized(ServletContextEvent event) {
        try {
            this.initEurekaEnvironment();
            this.initEurekaServerContext();
            ServletContext sc = event.getServletContext();
            sc.setAttribute(EurekaServerContext.class.getName(), this.serverContext);
        } catch (Throwable var3) {
            logger.error("Cannot bootstrap eureka server :", var3);
            throw new RuntimeException("Cannot bootstrap eureka server :", var3);
        }
    }
}
</code>
</pre>

直接看contextInitialized方法，发现里面的initEurekaServerContext()方法实现了注册相关的内容。

### 2.2 Ribbon客户端负载原理

未完待续

