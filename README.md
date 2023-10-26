# 메모 앱

## 요구사항 명세서

- 여기서 말한 '메모'는 제목, 내용, 작성일자를 포함한 데이터를 일컫음
- 메모가 저장 될 경우 메모가 입력되는 순서대로 자동으로 순번을 매겨 함께 위에서 언급된 내용과 함게 저장되어야 함
- 만약 기존에 저장된 '메모를 수정(제목 또는 내용, 아님 둘 다)될 경우 메모가 수정되는 최총수정일자를 추가로 함께 저장함
- 작성된 메모는 반드시 안드로이드에서 제공하는 내부 데이터베이스 기능을 이용해 저장되어야 함
- [앱 처음 실행 되면] 기존에 저장된 메모는 (최초)작성일자, 제목으로 구성된 목록으로 제공해야하며, 한 화면에 내용이 전부 출력되지 않은경우 상.하로 스크롤이 되어야 함.
  그리고 새로운 메모를 기록 할 수 있는 기능을 제공해야 함
- [앱 처음 화면에서 메모 추가 할 경우] 새로운 화면(액티비티)에서 메모의 제목과 내용을 기록할 수 있는 기능이 제공함. 최종적으로 메모가 저장될 때 제목과 내용 이외에 기록된
  일시(년-월-일 시간:분:초)도 자동으로 기록되어야 함
- [앱의 최초 화면의 목록에서 한 메모가 선택될 경우] 새로운 액티비티에 기존에 저장된 메모의 저장된 메모의 순번, (최초)작성일자, 최종수정일자(만약에 수정이 1번 이상 될
  경우 수정 내역이 없으면 출력이 되지 않음), 제목, 내용 순으로 출력되어야 하며, 해당 메모에 대한 기본적으 로 선택된 메모에 대한 수정, 삭제 기능을 제공함. 그리고 입이
  최초 실행되는 화면으로 돌아가 능 기능이 필요함.
- [앱의 메모 수정 화면] 선택된 메모의 제목과 내용이 수정 될 수 있는 기능을 제공해야 함. 또한, 해당 메모의 순번이 함께 출력이 되어야 함
- [앱이 최초 실행되는 화면] 기존에 저장된 메모는 검색이 되어야 하며, 검색결과는 목록 형태로 제공해야 함 (제목과 내용 데이터를 기반으로 검색되어야 함)
- [모든 화면] *뒤로가기* 버튼을 누르면 이전 화면(앱 최초 화면)으로 돌아가며, 해당 화면은 종료 되어야함