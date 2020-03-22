package com.wcq.tang.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OriginalExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public OriginalExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
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
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
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

        public Criteria andOriginalIdIsNull() {
            addCriterion("original_id is null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIsNotNull() {
            addCriterion("original_id is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalIdEqualTo(Long value) {
            addCriterion("original_id =", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotEqualTo(Long value) {
            addCriterion("original_id <>", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThan(Long value) {
            addCriterion("original_id >", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("original_id >=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThan(Long value) {
            addCriterion("original_id <", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdLessThanOrEqualTo(Long value) {
            addCriterion("original_id <=", value, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdIn(List<Long> values) {
            addCriterion("original_id in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotIn(List<Long> values) {
            addCriterion("original_id not in", values, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdBetween(Long value1, Long value2) {
            addCriterion("original_id between", value1, value2, "originalId");
            return (Criteria) this;
        }

        public Criteria andOriginalIdNotBetween(Long value1, Long value2) {
            addCriterion("original_id not between", value1, value2, "originalId");
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andOriginalDateIsNull() {
            addCriterion("original_date is null");
            return (Criteria) this;
        }

        public Criteria andOriginalDateIsNotNull() {
            addCriterion("original_date is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalDateEqualTo(Date value) {
            addCriterion("original_date =", value, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateNotEqualTo(Date value) {
            addCriterion("original_date <>", value, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateGreaterThan(Date value) {
            addCriterion("original_date >", value, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateGreaterThanOrEqualTo(Date value) {
            addCriterion("original_date >=", value, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateLessThan(Date value) {
            addCriterion("original_date <", value, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateLessThanOrEqualTo(Date value) {
            addCriterion("original_date <=", value, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateIn(List<Date> values) {
            addCriterion("original_date in", values, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateNotIn(List<Date> values) {
            addCriterion("original_date not in", values, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateBetween(Date value1, Date value2) {
            addCriterion("original_date between", value1, value2, "originalDate");
            return (Criteria) this;
        }

        public Criteria andOriginalDateNotBetween(Date value1, Date value2) {
            addCriterion("original_date not between", value1, value2, "originalDate");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andFormatIsNull() {
            addCriterion("format is null");
            return (Criteria) this;
        }

        public Criteria andFormatIsNotNull() {
            addCriterion("format is not null");
            return (Criteria) this;
        }

        public Criteria andFormatEqualTo(String value) {
            addCriterion("format =", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatNotEqualTo(String value) {
            addCriterion("format <>", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatGreaterThan(String value) {
            addCriterion("format >", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatGreaterThanOrEqualTo(String value) {
            addCriterion("format >=", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatLessThan(String value) {
            addCriterion("format <", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatLessThanOrEqualTo(String value) {
            addCriterion("format <=", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatLike(String value) {
            addCriterion("format like", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatNotLike(String value) {
            addCriterion("format not like", value, "format");
            return (Criteria) this;
        }

        public Criteria andFormatIn(List<String> values) {
            addCriterion("format in", values, "format");
            return (Criteria) this;
        }

        public Criteria andFormatNotIn(List<String> values) {
            addCriterion("format not in", values, "format");
            return (Criteria) this;
        }

        public Criteria andFormatBetween(String value1, String value2) {
            addCriterion("format between", value1, value2, "format");
            return (Criteria) this;
        }

        public Criteria andFormatNotBetween(String value1, String value2) {
            addCriterion("format not between", value1, value2, "format");
            return (Criteria) this;
        }

        public Criteria andFormatedIsNull() {
            addCriterion("formated is null");
            return (Criteria) this;
        }

        public Criteria andFormatedIsNotNull() {
            addCriterion("formated is not null");
            return (Criteria) this;
        }

        public Criteria andFormatedEqualTo(Boolean value) {
            addCriterion("formated =", value, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedNotEqualTo(Boolean value) {
            addCriterion("formated <>", value, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedGreaterThan(Boolean value) {
            addCriterion("formated >", value, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("formated >=", value, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedLessThan(Boolean value) {
            addCriterion("formated <", value, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedLessThanOrEqualTo(Boolean value) {
            addCriterion("formated <=", value, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedIn(List<Boolean> values) {
            addCriterion("formated in", values, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedNotIn(List<Boolean> values) {
            addCriterion("formated not in", values, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedBetween(Boolean value1, Boolean value2) {
            addCriterion("formated between", value1, value2, "formated");
            return (Criteria) this;
        }

        public Criteria andFormatedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("formated not between", value1, value2, "formated");
            return (Criteria) this;
        }

        public Criteria andCleanedIsNull() {
            addCriterion("cleaned is null");
            return (Criteria) this;
        }

        public Criteria andCleanedIsNotNull() {
            addCriterion("cleaned is not null");
            return (Criteria) this;
        }

        public Criteria andCleanedEqualTo(Boolean value) {
            addCriterion("cleaned =", value, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedNotEqualTo(Boolean value) {
            addCriterion("cleaned <>", value, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedGreaterThan(Boolean value) {
            addCriterion("cleaned >", value, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("cleaned >=", value, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedLessThan(Boolean value) {
            addCriterion("cleaned <", value, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedLessThanOrEqualTo(Boolean value) {
            addCriterion("cleaned <=", value, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedIn(List<Boolean> values) {
            addCriterion("cleaned in", values, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedNotIn(List<Boolean> values) {
            addCriterion("cleaned not in", values, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedBetween(Boolean value1, Boolean value2) {
            addCriterion("cleaned between", value1, value2, "cleaned");
            return (Criteria) this;
        }

        public Criteria andCleanedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("cleaned not between", value1, value2, "cleaned");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andTxtPathIsNull() {
            addCriterion("txt_path is null");
            return (Criteria) this;
        }

        public Criteria andTxtPathIsNotNull() {
            addCriterion("txt_path is not null");
            return (Criteria) this;
        }

        public Criteria andTxtPathEqualTo(String value) {
            addCriterion("txt_path =", value, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathNotEqualTo(String value) {
            addCriterion("txt_path <>", value, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathGreaterThan(String value) {
            addCriterion("txt_path >", value, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathGreaterThanOrEqualTo(String value) {
            addCriterion("txt_path >=", value, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathLessThan(String value) {
            addCriterion("txt_path <", value, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathLessThanOrEqualTo(String value) {
            addCriterion("txt_path <=", value, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathLike(String value) {
            addCriterion("txt_path like", value, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathNotLike(String value) {
            addCriterion("txt_path not like", value, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathIn(List<String> values) {
            addCriterion("txt_path in", values, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathNotIn(List<String> values) {
            addCriterion("txt_path not in", values, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathBetween(String value1, String value2) {
            addCriterion("txt_path between", value1, value2, "txtPath");
            return (Criteria) this;
        }

        public Criteria andTxtPathNotBetween(String value1, String value2) {
            addCriterion("txt_path not between", value1, value2, "txtPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathIsNull() {
            addCriterion("clean_path is null");
            return (Criteria) this;
        }

        public Criteria andCleanPathIsNotNull() {
            addCriterion("clean_path is not null");
            return (Criteria) this;
        }

        public Criteria andCleanPathEqualTo(String value) {
            addCriterion("clean_path =", value, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathNotEqualTo(String value) {
            addCriterion("clean_path <>", value, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathGreaterThan(String value) {
            addCriterion("clean_path >", value, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathGreaterThanOrEqualTo(String value) {
            addCriterion("clean_path >=", value, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathLessThan(String value) {
            addCriterion("clean_path <", value, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathLessThanOrEqualTo(String value) {
            addCriterion("clean_path <=", value, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathLike(String value) {
            addCriterion("clean_path like", value, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathNotLike(String value) {
            addCriterion("clean_path not like", value, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathIn(List<String> values) {
            addCriterion("clean_path in", values, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathNotIn(List<String> values) {
            addCriterion("clean_path not in", values, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathBetween(String value1, String value2) {
            addCriterion("clean_path between", value1, value2, "cleanPath");
            return (Criteria) this;
        }

        public Criteria andCleanPathNotBetween(String value1, String value2) {
            addCriterion("clean_path not between", value1, value2, "cleanPath");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table original
     *
     * @mbg.generated do_not_delete_during_merge Sun Mar 15 15:31:58 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table original
     *
     * @mbg.generated Sun Mar 15 15:31:58 CST 2020
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