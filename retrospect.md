# Retropective
> 회고를 시작하기 전 이 프로젝트의 진행에 대한 점수를 준다면(5점)
* 준호 : 3점 - 만나는날짜
* 지혜 : 3점 - 나빼고잘해
* 동현 : 4점 - 어중간하게

# Liked
* 다른 분들 작업하는 스타일을 알 수 있어서 좋았다.
  * 아 나도 이거 말하려고 했는데
* 회사말고 같은 주제로 뭔가 소통하는 건 좋았다.
* 코틀린을 써봐서 좋았고, test container도 좋았습니다.

# Learned
* 일정의 중요성
  * 다음에 이런 협업을 한다면 월요일을 날짜로 잡으면 안될 것 같다. 주말에 작업을 많이 하다보니까 작업분에 대해 피드백을 할려면 기간이 필요한데 월요일은 시간이 너무 없다.
    * 근데 작업량이 한 5~10분이면 피드백이 가능한 수준인데 시간이 없다는건 ?
    * 그래도 수정할려면 시간이 또 필요한데 피드백 적용을 할 시간이 부족하다는 느낌
    * 따로따로 개발하기로 영역을 나눠놨지만 최종 머지가 필요한 시점, 또는 순서가 있는 작업들에 대한 디펜던시가 있다.
    * 그럼 어떻게 했어야 할까요? 수요일에 했으면 될까요? 비슷하지 않을까요?
    * 그래도 월화수가 좀 바쁜일이 많아서 다른 날이었으면 조금은 나았을 것 같다.
    * 괜히 회사에서 배포를 수,목에 하는게 아니었다.
* 리더급이 셋이라고 자동으로 가지는 않는다.
  * 일정 매니징을 하려고 하다가, 뭐 정하지도 않았는데 하는게 맞나
  * 어쩌면 그냥도 가지 않을까?
  * 그런데 안 가더라
* 누군가에게 리딩을 잡도록 했어야 했다.
  * 나는 리딩을 하는 스타일은 아닌데, 동현님은 리딩을 하는 스타일인 것 같고 준호님은 아직 잘 모르겠고
  * 생각하는게 좀 달랐어서 기다려서 톤을 맞출려고 했던게 좀 있었는데, 리딩을 맡기고 호로록 했으면 의견이 다를지언정 프로젝트는 더 빠르지 않았을까?
  * 그것도 맞긴 맞는데, 뭐 우리가 이걸 꼭 성공시켜야 되는 그런건 아니니까 이런걸 안 것도 좋은 것 같다.

# Lacked
* 월화수에 너무 바빴는데, 그 부분을 미리 알리고 일정을 조금 조정했다면 어땠을까
* 코틀린에 너무 익숙하지가 않아서 되게 작은 수정도 시간이 조금 걸렸다.
* 대화가 좀 더 많았어야 할 것 같다.
  * 그래서 누가 좀 못하고 있을때 그냥 대신해서 쭉 치고 나갈수도 있는데, 처음에 시작할 때 속도를 맞추자 라고 했던게 기억이 나서 실패할 지언정 혼자 다 만들진 않겠다. 이런 각오가 있었다.
* 전반적인 플래닝이 좀 부족했던 것 같다.
  * 화면 개발 : e2e 라고 했을때, interface를 가지고 먼저 작업한다던가 너무 큰 덩어리로 쪼개지 않았나.
  * api 단위로 vertical로 나눴는데, 그래도 여기서 interface만 ticket을 분리했어도 조금 더 잘할 수 있지 않았을까?

# Longed for
* interface를 먼저 만들어서 작업간의 디펜던시를 제거하면 좋겠다.
* 도메인을 먼저 정의를 하고 서비스 레이어를 구현한게 좋았다.
  * 그래서 docker에 db 붙이는 것도 빨리 할 수 있었던 것 같다.
  * 그런데 나누어서 작업하다보니 id를 헷갈려서 쓴 부분들이 있었다.
  * domain entity 기준으로 작업하는 건 좋았는데 interface와 domain간의 차이를 잘 봐야 될것 같고, 어떻게 실수하지 않을 수 있을지 생각해봐야겠다.
* 다음에 이런 개발을 한다면, 개인 pc에라도 배포환경을 구성해놓고 배포 해놓고 하면 좋을 것 같다.

# End of retro
> 회고를 끝나고 나서 다음에 이 프로젝트를 진행한다면 몇점을 줄 수 있을까?
* 준호 : 4점
* 지혜 : 4점
* 동현 : 4점
