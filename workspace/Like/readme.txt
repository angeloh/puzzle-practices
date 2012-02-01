Java Test

1)
I designed three cache classes under "com.test" and used factory pattern to build the class when multiple instances needed. 
1. FIFOCacheQueue 
2. FixedCacheQueue 
3. LRUCacheQueue 
Please see the attached file. 

We can use open source cache libraries, such as: 
· Java Caching System (JCS)
· Ehcache 

2) Write a program that will read the content of a text file and save it in reverse order
The class ReverseTextFile.java is attached at "com.test" 
Usage: java ReverseTextFile in out 

3) 
Please see "com.model" and "com.util" for implementations. (hibernate.cfg.xml is provided.) 

4)

Please see "com.dao" and "com.dao.hibernate" for implementations.

In order to make easier to deploy, applicationContext-resources.xml can get database properties from build.xml or build.properties. 
or use hibernate.properties to configure hibernate specific properties. 
1. applicationContext-hibernate.xml 
2. applicationContext-resources.xml 
3. hibernate.properties 

Cache configuration file for using ehcache is provided. 
4. eh-cache.xml