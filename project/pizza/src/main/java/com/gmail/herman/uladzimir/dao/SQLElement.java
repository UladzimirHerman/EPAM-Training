package com.gmail.herman.uladzimir.dao;

/**
 * Class {@link SQLElement} is a final class, which contains only constant fields,
 * which define keys from property-file.
 *
 * @author Uladzimir Herman
 */
public final class SQLElement {

    public static final String SQL_FILE_NAME = "sql_element";

    public static final String COMMON_FIELD_TOTAL_ROWS = "common.field.totalRows";

    public static final String FEEDBACK_FIELD_ID = "feedback.field.id";
    public static final String FEEDBACK_FIELD_USER_ID = "feedback.field.userId";
    public static final String FEEDBACK_FIELD_COMMENT = "feedback.field.comment";
    public static final String FEEDBACK_FIELD_RATING = "feedback.field.rating";
    public static final String FEEDBACK_FIELD_DATE = "feedback.field.date";
    public static final String FEEDBACK_QUERY_FIND_ALL = "feedback.query.findAll";
    public static final String FEEDBACK_QUERY_FIND_BY_ID = "feedback.query.findById";
    public static final String FEEDBACK_QUERY_INSERT = "feedback.query.insert";
    public static final String FEEDBACK_QUERY_UPDATE = "feedback.query.update";
    public static final String FEEDBACK_QUERY_DELETE_BY_ID = "feedback.query.deleteById";
    public static final String FEEDBACK_QUERY_COUNT = "feedback.query.count";

    public static final String NEWS_FIELD_ID = "news.field.id";
    public static final String NEWS_FIELD_USER_ID = "news.field.userId";
    public static final String NEWS_FIELD_TITLE = "news.field.title";
    public static final String NEWS_FIELD_CONTENT = "news.field.content";
    public static final String NEWS_FIELD_PHOTO = "news.field.photo";
    public static final String NEWS_FIELD_DATE = "news.field.date";
    public static final String NEWS_QUERY_FIND_ALL = "news.query.findAll";
    public static final String NEWS_QUERY_FIND_BY_ID = "news.query.findById";
    public static final String NEWS_QUERY_INSERT = "news.query.insert";
    public static final String NEWS_QUERY_UPDATE = "news.query.update";
    public static final String NEWS_QUERY_DELETE_BY_ID = "news.query.deleteById";
    public static final String NEWS_QUERY_COUNT = "news.query.count";

    public static final String ORDER_FIELD_ID = "order.field.id";
    public static final String ORDER_FIELD_DATE = "order.field.date";
    public static final String ORDER_FIELD_USER_ID = "order.field.userId";
    public static final String ORDER_FIELD_STATUS = "order.field.status";
    public static final String ORDER_QUERY_FIND_ALL = "order.query.findAll";
    public static final String ORDER_QUERY_FIND_BY_ID = "order.query.findById";
    public static final String ORDER_QUERY_INSERT = "order.query.insert";
    public static final String ORDER_QUERY_UPDATE = "order.query.update";
    public static final String ORDER_QUERY_DELETE_BY_ID = "order.query.deleteById";
    public static final String ORDER_QUERY_COUNT = "order.query.count";
    public static final String ORDER_QUERY_COUNT_ARCHIVE = "order.query.countArchive";
    public static final String ORDER_QUERY_COUNT_ARCHIVE_USER = "order.query.countArchiveUser";
    public static final String ORDER_QUERY_COUNT_BASKET = "order.query.countBasket";
    public static final String ORDER_QUERY_COUNT_OPEN = "order.query.countOpen";
    public static final String ORDER_QUERY_COUNT_OPEN_USER = "order.query.countOpenUser";
    public static final String ORDER_QUERY_FIND_ARCHIVE = "order.query.findArchive";
    public static final String ORDER_QUERY_FIND_ARCHIVE_USER = "order.query.findArchiveUser";
    public static final String ORDER_QUERY_FIND_BASKET = "order.query.findBasket";
    public static final String ORDER_QUERY_FIND_OPEN = "order.query.findOpen";
    public static final String ORDER_QUERY_FIND_OPEN_USER = "order.query.findOpenUser";

    public static final String ORDER_INFO_FIELD_ID = "orderInfo.field.id";
    public static final String ORDER_INFO_FIELD_ORDER_ID = "orderInfo.field.orderId";
    public static final String ORDER_INFO_FIELD_PRODUCT_ID = "orderInfo.field.productId";
    public static final String ORDER_INFO_FIELD_QUANTITY = "orderInfo.field.quantity";
    public static final String ORDER_INFO_QUERY_FIND_ALL = "orderInfo.query.findAll";
    public static final String ORDER_INFO_QUERY_FIND_BY_ID = "orderInfo.query.findById";
    public static final String ORDER_INFO_QUERY_INSERT = "orderInfo.query.insert";
    public static final String ORDER_INFO_QUERY_UPDATE = "orderInfo.query.update";
    public static final String ORDER_INFO_QUERY_DELETE_BY_ID = "orderInfo.query.deleteById";
    public static final String ORDER_INFO_QUERY_COUNT = "orderInfo.query.count";
    public static final String ORDER_INFO_QUERY_FIND_BY_ORDER_ID = "orderInfo.query.findByOrderId";

    public static final String PRODUCT_FIELD_ID = "product.field.id";
    public static final String PRODUCT_FIELD_NAME = "product.field.name";
    public static final String PRODUCT_FIELD_DESCRIPTION = "product.field.description";
    public static final String PRODUCT_FIELD_PRICE = "product.field.price";
    public static final String PRODUCT_FIELD_PHOTO = "product.field.photo";
    public static final String PRODUCT_FIELD_SALE = "product.field.sale";
    public static final String PRODUCT_QUERY_FIND_ALL = "product.query.findAll";
    public static final String PRODUCT_QUERY_FIND_BY_ID = "product.query.findById";
    public static final String PRODUCT_QUERY_INSERT = "product.query.insert";
    public static final String PRODUCT_QUERY_UPDATE = "product.query.update";
    public static final String PRODUCT_QUERY_DELETE_BY_ID = "product.query.deleteById";
    public static final String PRODUCT_QUERY_COUNT = "product.query.count";
    public static final String PRODUCT_QUERY_COUNT_FOR_SALE = "product.query.countForSale";
    public static final String PRODUCT_QUERY_FIND_FOR_SALE = "product.query.findForSale";

    public static final String USER_FIELD_ID = "user.field.id";
    public static final String USER_FIELD_LOGIN = "user.field.login";
    public static final String USER_FIELD_PASSWORD = "user.field.password";
    public static final String USER_FIELD_ROLE = "user.field.role";
    public static final String USER_QUERY_FIND_ALL = "user.query.findAll";
    public static final String USER_QUERY_FIND_BY_ID = "user.query.findById";
    public static final String USER_QUERY_INSERT = "user.query.insert";
    public static final String USER_QUERY_UPDATE = "user.query.update";
    public static final String USER_QUERY_DELETE_BY_ID = "user.query.deleteById";
    public static final String USER_QUERY_COUNT = "user.query.count";
    public static final String USER_QUERY_FIND_BY_LOGIN = "user.query.findByLogin";

    public static final String USER_INFO_FIELD_ID = "userInfo.field.id";
    public static final String USER_INFO_FIELD_NAME = "userInfo.field.name";
    public static final String USER_INFO_FIELD_SURNAME = "userInfo.field.surname";
    public static final String USER_INFO_FIELD_PHONE = "userInfo.field.phone";
    public static final String USER_INFO_FIELD_CITY = "userInfo.field.city";
    public static final String USER_INFO_FIELD_STREET = "userInfo.field.street";
    public static final String USER_INFO_FIELD_BUILDING = "userInfo.field.building";
    public static final String USER_INFO_FIELD_HOUSING = "userInfo.field.housing";
    public static final String USER_INFO_FIELD_APARTMENT = "userInfo.field.apartment";
    public static final String USER_INFO_FIELD_NOTE = "userInfo.field.note";
    public static final String USER_INFO_QUERY_FIND_ALL = "userInfo.query.findAll";
    public static final String USER_INFO_QUERY_FIND_BY_ID = "userInfo.query.findById";
    public static final String USER_INFO_QUERY_INSERT = "userInfo.query.insert";
    public static final String USER_INFO_QUERY_UPDATE = "userInfo.query.update";
    public static final String USER_INFO_QUERY_DELETE_BY_ID = "userInfo.query.deleteById";
    public static final String USER_INFO_QUERY_COUNT = "userInfo.query.count";

}