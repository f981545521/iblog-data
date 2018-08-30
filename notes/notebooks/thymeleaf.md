### Thymeleaf

- th:insert   ：保留自己的主标签，保留th:fragment的主标签。
- th:replace ：不要自己的主标签，保留th:fragment的主标签。
- th:include ：保留自己的主标签，不要th:fragment的主标签。（官方3.0后不推荐）


<head>
    <th:block th:include="/common/header::commonHeader('欢迎您')"/>
    <th:block th:include="/common/header::referrer"/>
</head>





