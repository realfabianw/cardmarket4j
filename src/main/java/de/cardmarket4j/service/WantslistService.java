package de.cardmarket4j.service;

import com.google.gson.JsonElement;
import de.cardmarket4j.AbstractService;
import de.cardmarket4j.CardMarketService;
import de.cardmarket4j.entity.Wantslist;
import de.cardmarket4j.entity.WantslistItem;
import de.cardmarket4j.entity.enumeration.Game;
import de.cardmarket4j.entity.enumeration.HTTPMethod;
import de.cardmarket4j.entity.enumeration.ItemType;
import de.cardmarket4j.entity.enumeration.Language;
import de.cardmarket4j.util.JsonIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * WantslistService provides wants list management.
 *
 * @see https://api.cardmarket.com/ws/documentation/API_2.0:Wants_List_Management
 * @author Hangovers
 * @version 0.1
 *
 */
public class WantslistService extends AbstractService {

    public WantslistService(CardMarketService cardMarket) {
        super(cardMarket);
    }

    /**
     * Returns a list with all of the user's wantslists, their name, associated game, and item count
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist
     * @return {@code List<Wantslist> listWantslist}
     * @throws IOException
     * @version 0.1
     */
    public List<Wantslist> getWantslist() throws IOException {
        List<Wantslist>  listWantslist = new ArrayList<>();
        JsonElement response = request("wantslist", HTTPMethod.GET);
        for (JsonElement jEle : response.getAsJsonObject().getAsJsonArray()) {
            listWantslist.add(JsonIO.getGson().fromJson(jEle, Wantslist.class));
        }
        return listWantslist;
    }

    /**
     * Creates a wantlist
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist
     * @param name, game
     * @throws IOException
     * @version 0.1
     */
    public boolean createWantslist(String name, Game game) throws IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "<wantslist>" +
                "<name>" + name + "</name>" +
                "<game>" + game.getId() + "</game>" +
                "</wantslist>";
        JsonElement response = request("wantslist", HTTPMethod.POST, xml);
        return getLastResponse().getResponseCode() == 200;
    }


    /**
     * Returns the single specified wantslist with its details and items
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist_Item
     * @param idWantslist
     * @return {@code Wantslist wantsList}
     * @throws IOException
     * @version 0.1
     */
    public Wantslist getWantslist(int idWantslist) throws IOException {
        JsonElement response = request("wantslist/" + idWantslist, HTTPMethod.GET);
        return JsonIO.getGson().fromJson(response.getAsJsonObject().get("wantslist"), Wantslist.class);
    }

    /**
     * Changes the name of the wantslist
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist_Item
     * @param idWantslist, newName
     * @throws IOException
     * @version 0.1
     */
    public boolean editWantslistName(int idWantslist, String newName) throws IOException {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        xml.append("<request>");
        xml.append("<action>editWantslist</action>");
        xml.append("<name>" + newName + "</name>");
        xml.append("</request>");
        JsonElement response = request("wantslist/" + idWantslist, HTTPMethod.PUT, xml.toString());
        return getLastResponse().getResponseCode() == 200;
    }

    /**
     * Add an item to the wantslist
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist_Item
     * @param idWantslist, item
     * @throws IOException
     * @version 0.1
     */
    public boolean addItem(int idWantslist, WantslistItem item) throws IOException {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        xml.append("<request>");
        xml.append("<action>addItem</action>");
        appendItemData(item, xml);
        xml.append("</request>");

        JsonElement response = request("wantslist/" + idWantslist, HTTPMethod.PUT, xml.toString());
        return getLastResponse().getResponseCode() == 200;
    }

    /**
     * Add multiple items to the wantslist
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist_Item
     * @param idWantslist, itemList
     * @throws IOException
     * @version 0.1
     */
    public boolean addItemList(int idWantslist, List<WantslistItem> itemList) throws IOException {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        xml.append("<request>");
        xml.append("<action>addItem</action>");
        for(WantslistItem item : itemList) {
            appendItemData(item, xml);
        }
        xml.append("</request>");
        JsonElement response = request("wantslist/" + idWantslist, HTTPMethod.PUT, xml.toString());
        return getLastResponse().getResponseCode() == 200;
    }

    /**
     * Add an item to the wantslist
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist_Item
     * @param idWantslist, item
     * @throws IOException
     * @version 0.1
     */
    public boolean editItem(int idWantslist, WantslistItem item) throws IOException {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        xml.append("<request>");
        xml.append("<action>editItem</action>");
        xml.append("<want>");
        xml.append("<idWant>" + item.getIdWant() + "</idWant>");
        xml.append("<count>" + item.getCount() + "</count>");
        xml.append("<minCondition>" + item.getMinCondition() + "</minCondition>");
        xml.append("<wishPrice>" + item.getWishPrice() + "</wishPrice>");
        xml.append("<mailAlert>" + item.isMailAlert() + "</mailAlert>");
        addIdLanguageIfPresent(item.getIdLanguage(), xml);
        xml.append("<isFoil>" + item.isFoil() + "</isFoil>");
        xml.append("<isAltered>" + item.isAltered() + "</isAltered>");
        //xml.append("<isPlayset />"); ???
        xml.append("<isSigned>" + item.isSigned() + "</isSigned>");
        xml.append("<isFirstEd>" + item.isFirstEd() + "</isFirstEd>");
        xml.append("</want>");
        xml.append("</request>");

        JsonElement response = request("wantslist/" + idWantslist, HTTPMethod.PUT, xml.toString());
        return getLastResponse().getResponseCode() == 200;
    }

    /**
     * Add an item to the wantslist
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist_Item
     * @param idWantslist, idWant
     * @throws IOException
     * @version 0.1
     */
    public boolean deleteItem(int idWantslist, int idWant) throws IOException {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        xml.append("<request>");
        xml.append("<action>deleteItem</action>");
        xml.append("<want>");
        xml.append("<idWant>" + idWant  + "</idWant>");
        xml.append("</want>");
        xml.append("</request>");

        JsonElement response = request("wantslist/" + idWantslist, HTTPMethod.PUT, xml.toString());
        return getLastResponse().getResponseCode() == 200;
    }



    /**
     * Deletes the specified wantslist and all items on it
     *
     * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Wantslist_Item
     * @param idWantslist
     * @throws IOException
     * @version 0.1
     */
    public boolean deleteWantslist(int idWantslist) throws IOException {
        JsonElement response = request("wantslist/" + idWantslist, HTTPMethod.DELETE);
        return getLastResponse().getResponseCode() == 200;
    }

    private void addIdLanguageIfPresent(List<Language> idLanguageList, StringBuilder xml) {
        if(idLanguageList != null && !idLanguageList.isEmpty()) {
            xml.append("<idLanguage>" + idLanguageList.toString() + "</idLanguage>");
        }
    }

    private static void checkItemType(WantslistItem item, StringBuilder xml) {
        if(item.getType().equals(ItemType.METAPRODUCT)) {
            xml.append("<metaproduct>");
            xml.append("<idMetaproduct>" + item.getIdProduct() + "</idMetaproduct>");
        } else {
            xml.append("<product>");
            xml.append("<idProduct>" + item.getIdProduct() + "</idProduct>");
        }
    }

    private static void endItemType(WantslistItem item, StringBuilder xml) {
        if(item.getType().equals(ItemType.METAPRODUCT)) {
            xml.append("</metaproduct>");
        } else {
            xml.append("</product>");
        }
    }

    private void appendItemData(WantslistItem item, StringBuilder xml) {
        checkItemType(item, xml);
        xml.append("<count>" + item.getCount() + "</count>");
        xml.append("<minCondition>" + item.getMinCondition() + "</minCondition>");
        xml.append("<wishPrice>" + item.getWishPrice() + "</wishPrice>");
        xml.append("<mailAlert>" + item.isMailAlert() + "</mailAlert>");
        addIdLanguageIfPresent(item.getIdLanguage(), xml);
        xml.append("<isFoil>" + item.isFoil() + "</isFoil>");
        xml.append("<isAltered>" + item.isAltered() + "</isAltered>");
        //xml.append("<isPlayset />"); ???
        xml.append("<isSigned>" + item.isSigned() + "</isSigned>");
        xml.append("<isFirstEd>" + item.isFirstEd() + "</isFirstEd>");
        endItemType(item, xml);
    }
}

