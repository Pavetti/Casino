package pl.pavetti.rockpaperscissors.game.gui.findenemygui;

import org.bukkit.Material;
import pl.pavetti.rockpaperscissors.config.file.GuiConfig;
import pl.pavetti.rockpaperscissors.config.model.GuiItemModel;
import pl.pavetti.rockpaperscissors.util.TextUtil;
import xyz.xenondevs.inventoryaccess.component.ComponentWrapper;
import xyz.xenondevs.invui.gui.PagedGui;
import xyz.xenondevs.invui.item.ItemProvider;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.controlitem.PageItem;

import java.util.List;
import java.util.Map;

public class PageBackItem extends PageItem {

    private final GuiConfig guiConfig;

    public PageBackItem(GuiConfig guiConfig) {
        super(false);
        this.guiConfig = guiConfig;
    }

    @Override
    public ItemProvider getItemProvider(PagedGui<?> gui) {
        GuiItemModel pageBackItemModel = guiConfig.getFindEnemyGuiModel().items().get( "pageBack" );
        ItemBuilder builder = new ItemBuilder(pageBackItemModel.material());
        builder.setDisplayName(TextUtil.wrapTextToXenoComponent( pageBackItemModel.name() ));
        if( gui.hasPreviousPage() ){
            builder.setLore( getPageItemLoreFrom( pageBackItemModel.hasNextPageLore(), gui ) );
        } else {
            builder.setLore( getPageItemLoreFrom( pageBackItemModel.hasNotNextPageLore(), gui ) );
        }

        return builder;
    }

    private List<ComponentWrapper> getPageItemLoreFrom(List<String> lore, PagedGui<?> gui){
        lore.forEach( line -> line
                .replace( "{CURRENT}", String.valueOf( gui.getCurrentPage() ) )
                .replace( "{MAX}", String.valueOf( gui.getPageAmount() ) )
            );
        return lore.stream().map( TextUtil::wrapTextToXenoComponent ).toList();
    }

}
