# 초보개발자 모임 제작겸 DDD 연습 프로젝트
### 구조
* 패키지구조
  - posting
  - reply
  - member


### 중점 사항
* 페이징이 필요한 entity는 N:1 관계를 맺는다.
  - 1:N관계를 많이들 생각하는데, 1:N의 경우 하위 entity를 전체조회 할수밖에 없기 때문에 페이징할때마다 전체조회로 성능상 이슈가 발생한다.
  - 하지만 N:1 관계로 하고 N의 repository 에서 1의 id로 조회를 하게 될 경우 위 이슈를 해결할 수 있다.
  - ex) ```replyRepository.findByPostingIdx (posting.getIdx())``` 

* setter는 생성하지 않는다.
  - 의도가 명확하지 않은 entity의 상태 변경을 막기 위해
  - 상태변경은 의도가 명확한 메소드명으로 생성할 것
  - ex) cancel(), shuffle() 등
  