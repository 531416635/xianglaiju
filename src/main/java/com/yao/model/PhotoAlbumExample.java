package com.yao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhotoAlbumExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PhotoAlbumExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAlbumnameIsNull() {
            addCriterion("albumname is null");
            return (Criteria) this;
        }

        public Criteria andAlbumnameIsNotNull() {
            addCriterion("albumname is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumnameEqualTo(String value) {
            addCriterion("albumname =", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameNotEqualTo(String value) {
            addCriterion("albumname <>", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameGreaterThan(String value) {
            addCriterion("albumname >", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameGreaterThanOrEqualTo(String value) {
            addCriterion("albumname >=", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameLessThan(String value) {
            addCriterion("albumname <", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameLessThanOrEqualTo(String value) {
            addCriterion("albumname <=", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameLike(String value) {
            addCriterion("albumname like", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameNotLike(String value) {
            addCriterion("albumname not like", value, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameIn(List<String> values) {
            addCriterion("albumname in", values, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameNotIn(List<String> values) {
            addCriterion("albumname not in", values, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameBetween(String value1, String value2) {
            addCriterion("albumname between", value1, value2, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumnameNotBetween(String value1, String value2) {
            addCriterion("albumname not between", value1, value2, "albumname");
            return (Criteria) this;
        }

        public Criteria andAlbumpathIsNull() {
            addCriterion("albumpath is null");
            return (Criteria) this;
        }

        public Criteria andAlbumpathIsNotNull() {
            addCriterion("albumpath is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumpathEqualTo(String value) {
            addCriterion("albumpath =", value, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathNotEqualTo(String value) {
            addCriterion("albumpath <>", value, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathGreaterThan(String value) {
            addCriterion("albumpath >", value, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathGreaterThanOrEqualTo(String value) {
            addCriterion("albumpath >=", value, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathLessThan(String value) {
            addCriterion("albumpath <", value, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathLessThanOrEqualTo(String value) {
            addCriterion("albumpath <=", value, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathLike(String value) {
            addCriterion("albumpath like", value, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathNotLike(String value) {
            addCriterion("albumpath not like", value, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathIn(List<String> values) {
            addCriterion("albumpath in", values, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathNotIn(List<String> values) {
            addCriterion("albumpath not in", values, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathBetween(String value1, String value2) {
            addCriterion("albumpath between", value1, value2, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumpathNotBetween(String value1, String value2) {
            addCriterion("albumpath not between", value1, value2, "albumpath");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeIsNull() {
            addCriterion("albumtime is null");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeIsNotNull() {
            addCriterion("albumtime is not null");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeEqualTo(Date value) {
            addCriterion("albumtime =", value, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeNotEqualTo(Date value) {
            addCriterion("albumtime <>", value, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeGreaterThan(Date value) {
            addCriterion("albumtime >", value, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("albumtime >=", value, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeLessThan(Date value) {
            addCriterion("albumtime <", value, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeLessThanOrEqualTo(Date value) {
            addCriterion("albumtime <=", value, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeIn(List<Date> values) {
            addCriterion("albumtime in", values, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeNotIn(List<Date> values) {
            addCriterion("albumtime not in", values, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeBetween(Date value1, Date value2) {
            addCriterion("albumtime between", value1, value2, "albumtime");
            return (Criteria) this;
        }

        public Criteria andAlbumtimeNotBetween(Date value1, Date value2) {
            addCriterion("albumtime not between", value1, value2, "albumtime");
            return (Criteria) this;
        }

        public Criteria andParam1IsNull() {
            addCriterion("param1 is null");
            return (Criteria) this;
        }

        public Criteria andParam1IsNotNull() {
            addCriterion("param1 is not null");
            return (Criteria) this;
        }

        public Criteria andParam1EqualTo(String value) {
            addCriterion("param1 =", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotEqualTo(String value) {
            addCriterion("param1 <>", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1GreaterThan(String value) {
            addCriterion("param1 >", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1GreaterThanOrEqualTo(String value) {
            addCriterion("param1 >=", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1LessThan(String value) {
            addCriterion("param1 <", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1LessThanOrEqualTo(String value) {
            addCriterion("param1 <=", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1Like(String value) {
            addCriterion("param1 like", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotLike(String value) {
            addCriterion("param1 not like", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1In(List<String> values) {
            addCriterion("param1 in", values, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotIn(List<String> values) {
            addCriterion("param1 not in", values, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1Between(String value1, String value2) {
            addCriterion("param1 between", value1, value2, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotBetween(String value1, String value2) {
            addCriterion("param1 not between", value1, value2, "param1");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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