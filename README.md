# alicorn
과제전형을 위하여 만든 repo

## Overview
* 서버 데이터가 어떻게 올 지 생각하며 UI단 작성하였음.
  * 가짜 데이터를 사용하였음.
* databinding, MVVM 패턴 사용.

## folder 구성
* data
  * di : repository di용
  * model : ui 단에 쓰일 data class를 모아놓았음
  * network
    * NetworkDataSource.kt : base interface
    * di : Dispatcher.IO di용
    * fake : 가짜 데이터용
    * retrofit : Http 통신을 한다고 가정하고 작성.
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

## 구현시도 내역
* login : id : test/ pw : 1234 입력 시 login 되어보이도록 fake data 작성
* chatDetail : socket.io 사용해서 socket client 통신 코드 작성 (서버가 없어서 동작하진 않음).
* chatList : fake data list를 받아와서 데이터바인딩 처리.
* findUser : fake data로 연결된 유저 리스트를 가져오고, 텍스트 입력 후 키보드 엔터 선택 시 연결된 유저 리스트 데이터에서 filtering 된 list 가져오도록 함.
* signIn : 회원가입 선택 시 무조건 넘어가도록 처리하였음.
* profile 
  * Preference DataStore에 저장된 User가 있으면 profile을 보여주고, 없을 경우 로그인 하라는 화면이 나옴.
  * 로그아웃 시 preference DataStore의 데이터 제거.

