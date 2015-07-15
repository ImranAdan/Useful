class Functional {
    public static void main(String[] args) {
        List<String> a = Arrays.asList("hello", "Imran", "world");
        System.out.println(getStringsWithoutAs(a).toString() + "\n");
        System.out.println(addAnAdditionalAtoAllString(a).toString() + "\n");
    }

    // Map
    public static List<String> addAnAdditionalAtoAllString(List<String> a) {
        return a.stream().map(x ->
                x.concat("AA")).collect(Collectors.toList());
    }

    // Filter
    public static List<String>
        getStringsWithoutAs(List<String> stringList) {
            return stringList.stream().filter(x
                    -> !x.contains("a"))
                .collect(Collectors.toList());
        }
}
