# Java

## 스레드 동기화 하기

- synchronized method : 메서드를 동기화

- synchronized statement : 중괄호로 쌓여진 구문을 동기화. Object key를 지정하여야한다.

## Object key

    1. Object key
    2. Mornitor
    3. Lock-Free 동기화 기법
    4. Thread의 Life Cycle
    5. 마치며

Mornitor는 자바에서 사용하는 기본적인 동기화 방식입니다. Mornitor 방식은 Key를 이용하여 동기화된 임계구역에 들어갈 수 있고 동기화된 임계구역에는 Key를 가진 Thread만 접근가능합니다.

Mornitor의 기본 구성 요소는 Entry-Set과 Key Owner가 존재하며, Wait-Set이라는 구역이 있습니다.

Key Section

공식적인 명칭은 아니지만, 이해를 쉽도록 돕기 위해 Key Section(키 구역) 이라고 명명했습니다. Key Section에는 Entry Set과 Key Owner, Wait-Set 구역이 존재합니다. Key Section은 Object Key와 연관이 있으며 Object Key당 Key Section이라고 볼 수 있습니다. 예시로 두 개의 Object Key가 존재한다고 가정합니다.

```
AKey keyA = new AKey();
BKey keyB = new BKey();
```
