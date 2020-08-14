[링크](https://java-http-server-sample.herokuapp.com/)


의존성까지 추가한 jar 파일 만들기
~~~
$ mvn assembly:assembly
~~~

헤로쿠 로컬 웹 띄우기
~~~
$ heroku local web
~~~

heroku 브랜치를 헤로쿠에 업로드
~~~
$ git push heroku heroku:master 
~~~

헤로쿠 로그 보기
~~~
$ heroku logs --tail
~~~