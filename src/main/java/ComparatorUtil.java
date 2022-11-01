public abstract class ComparatorUtil {
    public static StudentComparator getStudentComparator(StudentComparatorVariant variant) {
        switch (variant) {
            case byFullName -> {
                return new StudentFNameComparator();
            }
            case byAvgExamScore -> {
                return new StudentAvgExamScoreComparator();
            }
            case byUniversityId -> {
                return new StudentUniversityIdComparator();
            }
            case byCurrentCourseNumber -> {
                return new StudentCurrentCourseNumberComparator();
            }
        }
        return null;
    }

    public static UniversityComparator getUniversityComparator(UniversityComparatorVariant variant) {
        switch (variant) {
            case byFullName -> {
                return new UniversityFullNameComparator();
            }
            case byId -> {
                return new UniversityIdComparator();
            }
            case byShortName -> {
                return new UniversityShortNameComparator();
            }
            case byMainProfile -> {
                return new UniversityMainProfileComparator();
            }
            case byYearOfFoundation -> {
                return new UniversityYearOfFoundationComparator();
            }
        }
        return null;
    }
}
