# 초보개발자 모임 제작겸 DDD 연습 프로젝트
### 구조
* 패키지구조
  - posting
    - domain
    - repository
  - reply
    - domain
    - repository
  - member
    - domain
    - repository


### 중점 사항
* 페이징이 필요한 entity는 N:1 관계를 맺는다.
  - 1:N 관계를 많이들 생각하는데, 1:N의 경우 **하위 entity를 전체조회** 할수밖에 없기 때문에 페이징할때마다 전체조회로 성능상 이슈가 발생한다.
  - 하지만 N:1 관계로 하고 N의 repository 에서 1의 id로 조회를 하게 될 경우 위 이슈를 해결할 수 있다.

* entity간의 관계에서 애그리거트(entity 군 혹은 집합)가 다른 entity간의 관계는 객체참조가 아닌 ID참조를 하되 같은 애그리거트일 경우엔 객체참조를 한다.
  - 애그리거트가 다른데 객체 참조를 하게 될 경우 물리적 DB 분리시에 분리영역이 모호해진다. 
  - ex) ```replyRepository.findByPostingIdx (posting.getIdx())``` 

* setter는 생성하지 않는다.
  - 의도가 명확하지 않은 entity의 상태 변경을 막기 위해
  - 상태변경은 의도가 명확한 메소드명으로 생성할 것
  - ex) cancel(), shuffle() 등

* 서비스 계층은 아래의 역할에만 충실히 한다.
  - 도메인 객체간의 실행흐름 제어 (즉, 도메인 로직은 도메인 객체에서 담당한다.)
    - 도메인 데이터와 도메인 로직이 한 영역에 위치하지 않으면 해당 로직을 파악하기 위해 여러 영역을 분석하게 된다.
    - 여러 서비스 계층에서 동일한 도메인 로직을 구현할 확률이 높다.
  - 트랜잭션 처리

### 알게된 사실
* Pageable의 Desc옵션은 limit 이후가 아니라 limit 이전에 적용된다.
  - 만약 ```new PageRequest(1, 30, Sort.Direction.DESC, "updateDate");``` 로 생성하면, DESC -> 2번째 페이지 -> 30개가 추출된다.
  - Repository의 결과는 Stream도 된다.
  - 추출된 데이터들 사이에서 좀 더 다양한 형태로 변경하고 싶을때는 Stream을 적극적으로 사용한다.

* 순수 View의 역할만 하는 Entity라면 **@Immutable**을 사용하여 변경사항을 무시하도록 하자.
  - @Subselect 를 통해 추출된 Entity의 경우 대부분이 View의 역할이다.
  - 하지만 실수로 해당 Entity를 수정하게 될 경우 JPA는 update 쿼리를 실행시킬 것이다.
  - 이를 코드상에서 방지하기 위해 @Immutable을 사용하자.

* 하나의 메소드에서 동일 Entity들의 변경 및 조회가 있을 경우 **@Synchronize**를 사용하자.
  - 예를 들어 Order Entity를 조회하여 상태를 변경후 OrderList를 조회할 경우 OrderList에는 변경된 Order Entity가 조회되어야 한다.
  - 하지만 JPA는 트랜잭션 종료 시점에서 update가 발생하므로 update 이전의 Order가 조회되버린다.
  - @Synchronize를 사용하면 로딩되는 엔티티에서 변경사항 발견시 flush를 먼저 실행시키고 로딩이 실행된다.

