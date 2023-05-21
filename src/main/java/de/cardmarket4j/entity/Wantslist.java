package de.cardmarket4j.entity;

import de.cardmarket4j.entity.enumeration.Game;

import java.util.List;
import java.util.Objects;

/**
 *
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Wantslist
 * @author Hangovers
 *
 */
public class Wantslist {

    private int idWantslist;
    private Game game;
    private String name;
    private int itemCount;
    private List<WantslistItem> items;

    public Wantslist(int idWantslist, Game game, String name, Integer itemCount, List<WantslistItem> items) {
        this.idWantslist = idWantslist;
        this.game = game;
        this.name = name;
        this.itemCount = itemCount;
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wantslist wantslist = (Wantslist) o;
        return idWantslist == wantslist.idWantslist && game == wantslist.game && name.equals(wantslist.name) && Objects.equals(itemCount, wantslist.itemCount) && Objects.equals(items, wantslist.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWantslist, game, name, itemCount, items);
    }

    public long getIdWantslist() {
        return idWantslist;
    }

    public void setIdWantslist(int idWantslist) {
        this.idWantslist = idWantslist;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public List<WantslistItem> getItems() {
        return items;
    }

    public void setItems(List<WantslistItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Wantslist{" +
                "idWantslist=" + idWantslist +
                ", game=" + game +
                ", name='" + name + '\'' +
                ", itemCount=" + itemCount +
                ", items=" + items +
                '}';
    }
}
