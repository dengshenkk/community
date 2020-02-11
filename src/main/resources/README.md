## bash
[MGB 自动生成sql](http://mybatis.org/generator/running/runningWithMaven.html)

```bash
1.设计好数据表
2.generatorConfig.xml中配置table标签,并指定表名和实体类名称
3.执行脚本
mvn -Dmybatis.generator.overwrite=false mybatis-generator:generate
```
