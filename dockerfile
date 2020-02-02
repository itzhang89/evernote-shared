FROM openjdk:8

COPY --chown=755 ./yinxiang/build/libs/yinxiang-0.0.1-SNAPSHOT.jar /

CMD java -jar /yinxiang-0.0.1-SNAPSHOT.jar \
    --yinxiang.dev-token=${devToken} \
    --yinxiang.note-store-url=${noteStoreUrl} \
    --spring.datasource.url=${dbUrl} \
    --spring.datasource.username=${dbUser} \
    --spring.datasource.password=${dbPassword}