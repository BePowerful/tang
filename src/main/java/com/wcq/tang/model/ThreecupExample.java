package com.wcq.tang.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThreecupExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public ThreecupExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andThreeIdIsNull() {
            addCriterion("three_id is null");
            return (Criteria) this;
        }

        public Criteria andThreeIdIsNotNull() {
            addCriterion("three_id is not null");
            return (Criteria) this;
        }

        public Criteria andThreeIdEqualTo(Long value) {
            addCriterion("three_id =", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdNotEqualTo(Long value) {
            addCriterion("three_id <>", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdGreaterThan(Long value) {
            addCriterion("three_id >", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("three_id >=", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdLessThan(Long value) {
            addCriterion("three_id <", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdLessThanOrEqualTo(Long value) {
            addCriterion("three_id <=", value, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdIn(List<Long> values) {
            addCriterion("three_id in", values, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdNotIn(List<Long> values) {
            addCriterion("three_id not in", values, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdBetween(Long value1, Long value2) {
            addCriterion("three_id between", value1, value2, "threeId");
            return (Criteria) this;
        }

        public Criteria andThreeIdNotBetween(Long value1, Long value2) {
            addCriterion("three_id not between", value1, value2, "threeId");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andPredicateIsNull() {
            addCriterion("predicate is null");
            return (Criteria) this;
        }

        public Criteria andPredicateIsNotNull() {
            addCriterion("predicate is not null");
            return (Criteria) this;
        }

        public Criteria andPredicateEqualTo(String value) {
            addCriterion("predicate =", value, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateNotEqualTo(String value) {
            addCriterion("predicate <>", value, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateGreaterThan(String value) {
            addCriterion("predicate >", value, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateGreaterThanOrEqualTo(String value) {
            addCriterion("predicate >=", value, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateLessThan(String value) {
            addCriterion("predicate <", value, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateLessThanOrEqualTo(String value) {
            addCriterion("predicate <=", value, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateLike(String value) {
            addCriterion("predicate like", value, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateNotLike(String value) {
            addCriterion("predicate not like", value, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateIn(List<String> values) {
            addCriterion("predicate in", values, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateNotIn(List<String> values) {
            addCriterion("predicate not in", values, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateBetween(String value1, String value2) {
            addCriterion("predicate between", value1, value2, "predicate");
            return (Criteria) this;
        }

        public Criteria andPredicateNotBetween(String value1, String value2) {
            addCriterion("predicate not between", value1, value2, "predicate");
            return (Criteria) this;
        }

        public Criteria andObjectIsNull() {
            addCriterion("object is null");
            return (Criteria) this;
        }

        public Criteria andObjectIsNotNull() {
            addCriterion("object is not null");
            return (Criteria) this;
        }

        public Criteria andObjectEqualTo(String value) {
            addCriterion("object =", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotEqualTo(String value) {
            addCriterion("object <>", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectGreaterThan(String value) {
            addCriterion("object >", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectGreaterThanOrEqualTo(String value) {
            addCriterion("object >=", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLessThan(String value) {
            addCriterion("object <", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLessThanOrEqualTo(String value) {
            addCriterion("object <=", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectLike(String value) {
            addCriterion("object like", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotLike(String value) {
            addCriterion("object not like", value, "object");
            return (Criteria) this;
        }

        public Criteria andObjectIn(List<String> values) {
            addCriterion("object in", values, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotIn(List<String> values) {
            addCriterion("object not in", values, "object");
            return (Criteria) this;
        }

        public Criteria andObjectBetween(String value1, String value2) {
            addCriterion("object between", value1, value2, "object");
            return (Criteria) this;
        }

        public Criteria andObjectNotBetween(String value1, String value2) {
            addCriterion("object not between", value1, value2, "object");
            return (Criteria) this;
        }

        public Criteria andUploaderIsNull() {
            addCriterion("uploader is null");
            return (Criteria) this;
        }

        public Criteria andUploaderIsNotNull() {
            addCriterion("uploader is not null");
            return (Criteria) this;
        }

        public Criteria andUploaderEqualTo(Long value) {
            addCriterion("uploader =", value, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderNotEqualTo(Long value) {
            addCriterion("uploader <>", value, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderGreaterThan(Long value) {
            addCriterion("uploader >", value, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderGreaterThanOrEqualTo(Long value) {
            addCriterion("uploader >=", value, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderLessThan(Long value) {
            addCriterion("uploader <", value, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderLessThanOrEqualTo(Long value) {
            addCriterion("uploader <=", value, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderIn(List<Long> values) {
            addCriterion("uploader in", values, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderNotIn(List<Long> values) {
            addCriterion("uploader not in", values, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderBetween(Long value1, Long value2) {
            addCriterion("uploader between", value1, value2, "uploader");
            return (Criteria) this;
        }

        public Criteria andUploaderNotBetween(Long value1, Long value2) {
            addCriterion("uploader not between", value1, value2, "uploader");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIsNull() {
            addCriterion("build_time is null");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIsNotNull() {
            addCriterion("build_time is not null");
            return (Criteria) this;
        }

        public Criteria andBuildTimeEqualTo(Date value) {
            addCriterion("build_time =", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotEqualTo(Date value) {
            addCriterion("build_time <>", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeGreaterThan(Date value) {
            addCriterion("build_time >", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("build_time >=", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeLessThan(Date value) {
            addCriterion("build_time <", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeLessThanOrEqualTo(Date value) {
            addCriterion("build_time <=", value, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeIn(List<Date> values) {
            addCriterion("build_time in", values, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotIn(List<Date> values) {
            addCriterion("build_time not in", values, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeBetween(Date value1, Date value2) {
            addCriterion("build_time between", value1, value2, "buildTime");
            return (Criteria) this;
        }

        public Criteria andBuildTimeNotBetween(Date value1, Date value2) {
            addCriterion("build_time not between", value1, value2, "buildTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table threecup
     *
     * @mbg.generated do_not_delete_during_merge Mon Mar 16 13:58:12 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table threecup
     *
     * @mbg.generated Mon Mar 16 13:58:12 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}