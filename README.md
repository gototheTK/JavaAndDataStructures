# Java

## ������ ����ȭ �ϱ�

- synchronized method : �޼��带 ����ȭ

- synchronized statement : �߰�ȣ�� �׿��� ������ ����ȭ. Object key�� �����Ͽ����Ѵ�.

## Object key

    1. Object key
    2. Mornitor
    3. Lock-Free ����ȭ ���
    4. Thread�� Life Cycle
    5. ��ġ��

Mornitor�� �ڹٿ��� ����ϴ� �⺻���� ����ȭ ����Դϴ�. Mornitor ����� Key�� �̿��Ͽ� ����ȭ�� �Ӱ豸���� �� �� �ְ� ����ȭ�� �Ӱ豸������ Key�� ���� Thread�� ���ٰ����մϴ�.

Mornitor�� �⺻ ���� ��Ҵ� Entry-Set�� Key Owner�� �����ϸ�, Wait-Set�̶�� ������ �ֽ��ϴ�.

Key Section

�������� ��Ī�� �ƴ�����, ���ظ� ������ ���� ���� Key Section(Ű ����) �̶�� ����߽��ϴ�. Key Section���� Entry Set�� Key Owner, Wait-Set ������ �����մϴ�. Key Section�� Object Key�� ������ ������ Object Key�� Key Section�̶�� �� �� �ֽ��ϴ�. ���÷� �� ���� Object Key�� �����Ѵٰ� �����մϴ�.

```
AKey keyA = new AKey();
BKey keyB = new BKey();
```
