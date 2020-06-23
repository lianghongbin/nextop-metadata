package com.nextop.metadata.entity;

/**
 * Description: 配置与配置之间的关系
 *
 * @author: eric.liang
 * @date: 6/23/20
 * @update:
 */
public class Relation {

    private String id;
    private Configure main;
    private Configure relation;
    private RelationType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Configure getMain() {
        return main;
    }

    public void setMain(Configure main) {
        this.main = main;
    }

    public Configure getRelation() {
        return relation;
    }

    public void setRelation(Configure relation) {
        this.relation = relation;
    }

    public RelationType getType() {
        return type;
    }

    public void setType(RelationType type) {
        this.type = type;
    }
}
