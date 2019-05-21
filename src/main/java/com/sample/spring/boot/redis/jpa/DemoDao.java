package com.sample.spring.boot.redis.jpa;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DemoDao<T> {

    private static BasicDataSource basicDataSource = new BasicDataSource();

    static {
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/mall");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("mysql");
    }

    private Class clazz;

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(basicDataSource);

    /**
     * 获取子类传过父类的泛型类的Class对象
     */
    public DemoDao() {
        clazz = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void exec(T bean) {
        Table table = (Table) clazz.getAnnotation(Table.class);
        Field[] fields = clazz.getDeclaredFields();
        StringBuffer sb = new StringBuffer();
        String colStr = "";
        List<Object> objList = new ArrayList<>();
        for (Field field : fields) {
            sb.append(field.getName() + ",");

            field.setAccessible(true);
            try {
                objList.add(field.get(bean));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        colStr = sb.substring(0, sb.length() - 1);
        log.info("实体类中的字段集合：{}", colStr);
        String insertSql = "insert into " + table.value() + "(" + colStr + ") values (";
        int size = objList.size();
        for (int i = 0; i < size; i++) {
            insertSql += "?, ";
        }

        String sql = insertSql.substring(0, insertSql.length() - 2);
        sql += ")";

        Object[] obje2 = objList.toArray(new Object[size]);

        log.info("要执行的sql语句为：{}", sql);
        int updateSum = jdbcTemplate.update(sql, obje2);

        log.info("更新的数量：{}", updateSum);
    }
}
