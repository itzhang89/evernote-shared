# ShareEverNote

#### Description

本项目主要是想将印象笔记中所有**Share**笔记的笔记内容，能够以博客网站的形式分享出来。

#### Software Architecture
Software architecture description

#### Installation

1.  确保 Docker > 18.09+
2.  确保 Java > 1.8
3.  确保**你有印象笔记的开发者Token**。官网申请链接[Developer Tokens](https://app.yinxiang.com/api/DeveloperToken.action)

#### Instructions

1.  将印象笔记中申请的 devToken 和 noteStoreUrl 设置到环境变量上, 如下。
    ```bash
    export devToken=S=s20:U=aaaaaaaa:E=bbbbbbbb:C=:P=1cd:A=en-devtoken:V=2:H=c5xxxxxxxxxxxx038c7684c
    export noteStoreUrl=https://app.yinxiang.com/shard/s20/notestore
    ```
2.  利用docker-compose 来启动 postgres 数据库。 `docker-compose -f yinxiang/src/main/resources/docker-compose.yml up`
3.  启动ShareEverNote应用。 ` ./gradlew clean bootRun `

#### Contribution

1.  Fork the repository
2.  Create Feat_xxx branch
3.  Commit your code
4.  Create Pull Request


#### Gitee Feature

1.  You can use Readme\_XXX.md to support different languages, such as Readme\_en.md, Readme\_zh.md
2.  Gitee blog [blog.gitee.com](https://blog.gitee.com)
3.  Explore open source project [https://gitee.com/explore](https://gitee.com/explore)
4.  The most valuable open source project [GVP](https://gitee.com/gvp)
5.  The manual of Gitee [https://gitee.com/help](https://gitee.com/help)
6.  The most popular members  [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
