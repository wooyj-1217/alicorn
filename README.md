# alicorn
과제전형을 위하여 만든 repo

## Overview
* 서버 데이터가 어떻게 올지 생각하며 UI단 작성하였음.
  * 가짜 데이터를 사용하였음.
* databinding, MVVM 패턴 사용.

## folder 구성
* data
  * model : ui 단에 쓰일 data class를 모아놓았음
  * preference : 로그인에 성공한 경우 return 값으로 돌려받은 userData를 저장하기 위한 preference DataStore.
* ui
  * adapter : RecyclerView에 부착하는 adapter들을 모아놓았음
  * pages : (임시) fragment, viewmodel을 모아놓았음. bottomNav 메뉴 item 별로 분리해서 한번 더 나누는게 더 깔끔할 것도 같음.
    * ChatDetailFragment : 채팅방 대화화면
    * ChatListFragment : 채팅방 리스트 화면
    * FindUserFragment : 대화 상대 검색 화면
    * LoginFragment : 로그인 화면
    * MyPageFragment : 마이 페이지 화면
    * SignInFragment : 회원가입 화면
  * util : BindingAdapter.kt(특정 값이 뷰에 어떻게 보일지를 조정해주는 function을 모아놓음.)

## 정리가 안된 부분
* 서버부착 X. 가짜데이터를 심어놓았음. //TODO 를 활용하여 서버 부착 시 필요한 부분들을 틈틈히 써놓긴 하였음.
* 가짜 데이터를 넣어도 서버가 붙을것을 생각해서 interface를 작성하고 이를 상속받아서 fake 데이터를 구성하는 식으로 갔어야 했는데 여력이 없었음. 이에 대해 한번 더 정리가 필요함.
* 채팅방 상세화면 부분에 데이터를 1초마다 interval로 data를 가져오도록 설정했는데, 소켓통신일 경우에는 방법을 달리하여 작성하여야 함.
* UI 정확하게 구현하지 않았음. 캡처화면을 참고하였으나 디테일은 다시 가다듬어야 함.




