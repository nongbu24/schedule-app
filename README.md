## 일정 생성 API

### API 정보

- Method: POST
- URL: /schedules

### Request
```json
{
  "title": "제목",
  "contents": "내용",
  "writer": "작성자",
  "password": "비밀번호"
}
```

### Response
```json
{
  "id": 1,
  "title": "제목",
  "contents": "내용",
  "writer": "작성자",
  "createdAt": "2026-04-12T12:00:00",
  "modifiedAt": "2026-04-12T12:00:00"
}
```

### 설명

- 일정을 생성합니다.

---

## 전체 일정 조회 API

### API 정보

- Method: GET
- URL: /schedules?writer=작성자

### Response
```json
[
    {
      "id": 1,
      "title": "제목",
      "contents": "내용",
      "writer": "작성자",
      "createdAt": "2026-04-12T12:00:00",
      "modifiedAt": "2026-04-12T12:00:00"
    }
]
```

### 설명

- 작성자를 기준으로 일정을 조회합니다.
- 작성자를 입력하지 않으면 저장된 전체 일정을 조회합니다.

---

## 선택 일정 조회 API

- Method: GET
- URL: /schedules/{scheduleId}

### Response
```json
{
  "id": 1,
  "title": "제목",
  "contents": "내용",
  "writer": "작성자",
  "createdAt": "2026-04-12T12:00:00",
  "modifiedAt": "2026-04-12T12:00:00"
}
```

### 설명

- id를 기준으로 일정을 조회합니다.

---

## 일정 수정 API

- Method: PUT
- URL: /schedules/{scheduleId}

### Request
```json
{
  "title": "제목바꿈",
  "writer": "작성자도바꿈",
  "password": "비밀번호"
}
```

### 인증/인가

- password 필요

### Response
```json
{
  "id": 1,
  "title": "제목바꿈",
  "contents": "내용",
  "writer": "작성자도바꿈",
  "createdAt": "2026-04-12T12:00:00",
  "modifiedAt": "2026-04-13T12:00:00"
}
```

### 설명

- id를 선택하여 제목과 작성자명을 수정합니다.
- 입력한 비밀번호가 틀렸다면 수정할 수 없습니다.

---

## 일정 삭제 API

- Method: DELETE
- URL: /schedules/{scheduleId}?password={schedulePassword}

### 인증/인가

- password 필요

### 설명

- id를 선택하여 일정을 삭제합니다.
- 이 때 비밀번호가 틀렸다면 일정을 삭제할 수 없습니다.

---

## ERD
<img width="293" height="186" alt="스크린샷 2026-04-13 오후 7 16 12" src="https://github.com/user-attachments/assets/36ce4b49-36dd-4c64-b066-b559b1bba7a6" />

---

#### 1. 3 Layer Architecture(Controller, Service, Repository)를 적절히 적용했는지 확인해 보고, 왜 이러한 구조가 필요한지 작성해 주세요.

저는 제가 적절히 적용했다고 생각합니다!
요청/응답 처리 + 비즈니스 로직 + DB 접근 이 3가지 역할을 가진 계층으로 나눠 코드가 덜 꼬이고, 더 쉽게 고칠 수 있게 만들어주기 때문에 3 Layer Architecture가 필요합니다.

#### 2. @RequestParam, @PathVariable, @RequestBody가 각각 어떤 어노테이션인지, 어떤 특징을 갖고 있는지 작성해 주세요.

@RequestParam은 URL의 ? 뒤의 오는 파라미터들을 처리할 때 사용합니다.
GET /schedule?writer=작성자 요청이 있다면 이 중 ? 뒤에 오는 writer=작성자가 쿼리 파라미터입니다.
required = false로 설정하지 않으면 파라미터가 필수가 됩니다. (defalut는 true)

@PathVariable은 URL 경로의 일부를 변수로 받을 때 사용합니다.
GET /schedules/123 형태의 요청에서 123 값을 받을 수 있습니다.

@RequestBody는 HTTP 요청의 Body에 있는ㄴ 데이터를 객체로 변환할 때 사용합니다.
주로 JSON 형태의 데이터를 받을 때 사용합니다.
