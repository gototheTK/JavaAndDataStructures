# 그래프

## 인접 행렬과 인접 리스트


## 최소 신장 트리

---

<br/>

---

### 신장 트리
순환 없이 모든 정점을 연결하는 그래프를 말합니다.

<br/>

### 최소 신장 트리
순환 없이 최소 간선 비용으로 모든 정점을 연결하는 그래프를 말합니다.

<br/>

### 절단
그래프에서 정점 집합을 분리하는 것을 말합니다.

<br/>

### 교차
절단된 정점 집합을 연결해주는 간선이 존재한다면, 절단이 간선을 교차한다고 합니다.

<br/>

### 존중
절단선을 기준으로 교차하지 않는 간선이 존재한다면,
절단이 간선을 존중한다고 합니다.

<br/>

### 경량 간선
교차하는 간선중 가중치가 최소힌 간선을 경량간선이라고 합니다.

<br/>

### 루프 불변성
여러 번 반복하더라도 성질에 대해 불변성을 만족하는 것을 말합니다.

<br/>

### 안전 간선
최소 신장 트리를 만족하며, 루프 불변성,경량 간선조건을 만족하면서 순환하지 않는 간선