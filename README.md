# Mission-Tetris
## java toy project with swing 
+ UI 
# ![main](https://user-images.githubusercontent.com/89013431/149329310-fc6ed8b8-51d9-4873-a6b7-e2450debf06a.png)
* 게임 룰
  + 기본 테트리스 게임 룰에 특정 시간 내에 주어진 개수만큼의 열을 깨야하는 미션을 추가했다.
    + 시작과 동시에 Mission Thread가 생성, 동작한다.
    + 미션을 클리어하면 종료 후 연이어 새로운 Mission Thread가 생성 동작하기를 게임 종료까지 반복 한다.
  + 블록 종류 
---
+ GameOver 조건
  + 블록이 끝까지 쌓였을 때
  + 미션을 시간 내에 수행하지 못했을 때
![gamovercase](https://user-images.githubusercontent.com/89013431/149329579-84267c17-fe03-48a4-bf4e-a11f4d5ed0cc.JPG)
##### 기본 테트리스 
