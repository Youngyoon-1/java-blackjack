# java-blackjack
##기능 구현
 - [o] 플레이어 이름을 입력받는다
    - [o] 영문자 이외의 값이 입력되면 안된다.(대소문자 구별 안한다)
 - [o] 첫턴에는 딜러와 등록된 플레이어에게 2장의 카드를 나눠준다.
    - [o] 딜러는 한장의 카드만 출력한다.
    - [o] 플레이어 들은 모든 카드를 출력한다.
 - [o] 모든 플레이어들은 원하는 만큼 카드를 입력받는다.
    - [o] 21이 넘어갈경우 더이상 카드를 받지 못하고 버스트, 다음 플레이어로 넘어간다.
 - [] 딜러는 정해진 규칙에 따라 카드를 추가 지급 받는다.
    - [] 16이하일 경우 한장의 카드를 추가 지급 받는다.
        - [] 21초과할 경우 딜러는 버스트한다.
    - [] 17이상일 경우 더이상 지급받지 않는다.
 - [] 각 플레이어별 점수를 계산한다.
    - [o] ACE는 1또는11로 계산한다.
        - [o] 11로 계산했을때 21이 초과하지 않으면 11로 계산한다.
        - [o] 21이 초과하면 1로 계산한다.
 - [] 승패를 정한다.
    - [] 플레이어가 버스트 될경우 무조건 플레이어가 패배한다.
    - [] 플레이어가 버스트 되지 않고 딜러가 버스트 되면 플레이어 승리한다.
    - [] 둘다 아닐 경우 점수를 계산하여 승패를 정한다.
    

## 우아한테크코스 코드리뷰
* [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)

