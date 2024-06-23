# 🏫 유한대학교 SpringBoot 기말프로젝트
**유한대학교 컴퓨터소프트웨어공학과 3학년 1학기 스프링부트 기말고사 프로젝트입니다.**

## 📋 주제
<h3>프로젝트의 주제는 <em style="display: inline; color: green;">학교포털 웹사이트</em>입니다.</h3>

### 기존 사이트와 다른 점
```texture
 기존 학교 포털시스템의 수강신청시스템은 전공은 선착순이 아니지만 교양은 선착순이므로 나에게 정말 필요한 강의지만 피치 못할 개인사정으로 인해 빠른수강신청을 하지 못할 경우 원하는 강의를 수강하지 못하게 됩니다.

 이런 상황을 조금이라도 줄이고자 교양 수강신청 시스템에 포인트 배팅 제도를 도입했습니다. 포인트 점수에 따라 개개인 별로 해당 강의에 대한 중요도가 나타나므로 좀 더 해당 강의가 필요한 학생이 들을 수 있게 됩니다. 

 교양수강신청에서 포인트 순위가 수강정원에 들지 못해 해당과목 수강신청 마감 후에 자동취소가 된다면 추가 수강신청에서 마감되지 않은 강의를 수강신청할 수 있습니다.
```
_~~동일포인트점수일 경우에는 선착순 입니다. 그렇기 때문에 초기 포인트를 적절하게 부여해야합니다.~~_
## 👨‍💻🧑‍💻 Developers
- <h2>👨‍💻 임성준</h2>

	- 프로젝트 기획
	- DB 설계
	- 회원관리 기능 구현
		- 학생 / 교수(관리자) 회원가입
		- 로그인 / 로그아웃
		- 회원 세션 처리
	- 강의관리 기능 구현
		- 강의 등록
		- 개설강의목록 조회
	- 수강신청 기능 구현
		- 개설강의목록 조회 및 검색기능
		- 전공수강신청
		- 교양수강신청
		- 추가수강신청
		- 수강신청목록 조회 및 검색기능
		- 수강신청취소
		- 수강신청 마감기능
	- 강의평가 기능 구현
		- 강의평가목록 조회 및 검색기능
		- 강의평가 등록
		
- <h2>🧑‍💻 이정민</h2>

	- 회원관리 기능 구현
		- 학번생성기능
	- 성적관리 기능 구현
		- 성적등록
		- 성적수정
		- 성적조회
	- 게시판관리 기능 구현
		- 글 등록
		- 글 수정
		- 글 삭제
		- 글 조회
## 💻 개발환경 및 기술스택
<h4>개발환경</h4>
<div>
	<img src="https://img.shields.io/badge/Visual Studio Code-007ACC?style=flat&logo=Visual Studio Code&logoColor=white" />
    <img src="https://img.shields.io/badge/Github-181717?style=flat&logo=Github&logoColor=white" />
</div>

<h4>FrontEnd</h4>
<div>
	<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white" />
	<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=CSS3&logoColor=white" />
	<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=white" />
</div>

<h4>BackEnd</h4>
<div>
	<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat&logo=Spring Boot&logoColor=white" />
	<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white" />
</div>

## 테이블 구조
### Student - 학생테이블
### Professor - 교수테이블
### Lecture - 강의테이블
### Enrolment - 수강신청테이블
### Evaluation - 강의평가테이블
### Grade - 성적테이블
### Board - 게시판테이블

## 기능설명
### 회원관리 기능(학생 / 교수)
- 학번생성
- 학생등록
- 회원가입
- 로그인 / 로그아웃
- 회원세션처리
### 강의관리 기능
- 강의등록
### 수강신청 기능
- 전공수강신청
- 교양수강신청
- 추가수강신청
- 수강신청현황 검색기능

### 강의평가 기능
- 강의평가 등록
- 강의평가 목록 검색기능
### 성적관리 기능

### 게시판 기능
- 글 등록
- 글 수정
- 글 삭제

## 보완할 점