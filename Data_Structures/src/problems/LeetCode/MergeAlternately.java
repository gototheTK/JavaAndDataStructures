class MergeAlternately {
    public String mergeAlternately(String word1, String word2) {

        char[] first = word1.toCharArray();
        char[] second = word2.toCharArray();
        char[] longer = first.length < second.length ? second : first;

        char[] answer = new char[first.length + second.length];

        int frontLength = first.length < second.length ? first.length : second.length;
        int backLength = first.length > second.length ? first.length : second.length;

        for(int i=0; i < frontLength; i++){
            answer[2*i] = first[i];
        }

        for(int i=0; i < frontLength; i++){
            answer[2*i+1] = second[i];
        }

        for(int i=frontLength; i < backLength; i++){
            answer[frontLength+i] = longer[i];
        }

        return String.valueOf(answer);
    }
}