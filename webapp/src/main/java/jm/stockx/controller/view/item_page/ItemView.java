package jm.stockx.controller.view.item_page;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import jm.stockx.controller.view.Item;
import jm.stockx.controller.view.head_form.element.ButtonItem;
import jm.stockx.controller.view.head_form.element.SizeSearch;

//@Route("")
@CssImport("./styles/css/itemView.css")
public class ItemView extends Div {
        private Div headBlockDiv = new Div();
        private Div anchorDiv = new Div();
        private Div namingDiv = new Div();
        private Div aboutPriceDiv = new Div();
        private Div viewerDiv = new Div();
        private Div descriptionDiv = new Div();
        private Item item;
        private Image image;

        public ItemView() {
            item = initItem();
            initItemView();
            initClass();
            add(
                    headBlockDiv);
        }

        private void initItemView() {
            ItemMenu itemMenu = new ItemMenu();
            ItemButton itemButton = new ItemButton();
            NameBlock nameBlock = new NameBlock();
            PriceBlock priceBlock = new PriceBlock();
            anchorDiv.add(itemMenu, itemButton);
            namingDiv.add(nameBlock);
            aboutPriceDiv.add(priceBlock);
            image = new Image("images/JordanJ1.png", "image");
            viewerDiv.add(image);
           headBlockDiv.add(
                   anchorDiv,
                   namingDiv,
                   aboutPriceDiv,
                   viewerDiv,
                   descriptionDiv
           );
        }

        private void initClass() {
            this.addClassName("item_view_style");
            headBlockDiv.addClassName("head_block_style");
            anchorDiv.addClassName("anchor_style");
            namingDiv.addClassName("naming_style");
            aboutPriceDiv.addClassName("about_price_style");
            viewerDiv.addClassName("viewer_style");
            descriptionDiv.addClassName("description_style");
            image.addClassName("image_style");
        }

        private Item initItem() {
            Item item = new Item();
            item.setCategory("SNEAKERS");
            item.setBrand("ADIDAS");
            item.setName("Jordan 1 Retro High Satin Snake Chicago (W)");
            item.setCondition("New");
            return item;
        }

        private class ItemMenu extends Div{
            UnorderedList listMenu = new UnorderedList();
            ListItem home = new ListItem("HOME");
            ListItem category = new ListItem(item.getCategory());
            ListItem brand = new ListItem(item.getBrand());
            ListItem name = new ListItem(item.getName().toUpperCase());
            ListItem separator1 = new ListItem("/");
            ListItem separator2 = new ListItem("/");
            ListItem separator3 = new ListItem("/");

            public ItemMenu() {
                initItemMenu();
                this.initClass();
                add(listMenu);
            }

            private void initItemMenu() {
                listMenu.add(home, separator1, category, separator2, brand, separator3, name);
            }

            private void initClass() {
                this.addClassName("item_menu_style");
                separator1.addClassName("separator_style");
                separator2.addClassName("separator_style");
                separator3.addClassName("separator_style");
            }

        }

        private class ItemButton extends Div{
            Button share = new Button( "SHARE", new Icon(VaadinIcon.ARROW_UP));
            Button portfolio= new Button( "PORTFOLIO", new Icon(VaadinIcon.PLUS));
            Button follow = new Button( "FOLLOW", new Icon(VaadinIcon.PLUS));

            public ItemButton() {
                initItemButton();
                this.initClass();
                add(share, portfolio, follow);
            }

            private void initItemButton() {

            }

            private void initClass() {
                this.addClassName("item_button_style");
            }

        }

        private class NameBlock extends Div{
            Div nameDiv = new Div();
            Div underNameDiv = new Div();
            H1 nameItem;
            UnorderedList underNameString = new UnorderedList();
            Span conditionText = new Span("Condition: ");
            Span conditionValue;
            ListItem condition = new ListItem();
            ListItem ticker = new ListItem("Ticker: JB-JO1RHSSGW");
            ListItem authentic = new ListItem();
            Anchor authenticUrl = new Anchor("#", "100% Authentic");

            public NameBlock() {
                initNameBlock();
                this.initClass();
                this.add(nameDiv, underNameDiv);
            }

            private void initNameBlock() {
                nameItem = new H1(item.getName());
                nameDiv.add(nameItem);
                conditionValue = new Span(item.getCondition());
                condition.add(conditionText, conditionValue);
                authentic.add(authenticUrl);
                underNameString.add(condition, ticker, authentic);
                underNameDiv.add(underNameString);
                underNameDiv.addClassName("under_name_style");

            }

            private void initClass() {
                nameDiv.addClassName("name_item_style");
                conditionValue.addClassName("under_name_decoration");
                authentic.addClassName("under_name_decoration");
                ticker.addClassName("ticker_style");
            }
        }

        private class PriceBlock extends Div{
            Div enterSize = new Div();
            Div lastSale = new Div();
            Div lowestAsk = new Div();
            Div highestBid = new Div();

            Span size = new Span("Size");
            Button sizeButton = new Button( "All", new Icon(VaadinIcon.ANGLE_DOWN));

            Div lastSaleString = new Div();
            Span sale = new Span("Last Sale");
            Span priceLastSale;
            Span differentMoney;
            Span differentPercent;
            Div underlastSale;
            ButtonItem buttonItem1;
            ButtonItem buttonItem2;
            SizeSearch sizeSearch1;
            SizeSearch sizeSearch2;

            public PriceBlock(){

                initPriceBlock();
                this.initClass();
                add(enterSize, lastSale, lowestAsk, highestBid);
            }

            private void initPriceBlock() {
                priceLastSale = new Span("$250");
                differentMoney = new Span("-$15");
                differentPercent = new Span("(-6%)");
                lastSaleString.add(sale, priceLastSale, new Icon(VaadinIcon.CARET_DOWN), differentMoney, differentPercent);
                sizeButton.setIconAfterText(true);
                enterSize.add(size, sizeButton);
                underlastSale = new SizeSearch("8M", "sales", "#", "#");
                lastSale.add(lastSaleString, underlastSale);
                buttonItem1 = new ButtonItem("260", "Buy", "Lowest Ask", "Bid");
                buttonItem2 = new ButtonItem("312", "Sell", "Highest Bid", "Ask");
                sizeSearch1 = new SizeSearch("--", "Asks", "#", "#");
                sizeSearch2 = new SizeSearch("--", "Bids", "#", "#");
                lowestAsk.add(buttonItem1, sizeSearch1);
                highestBid.add(buttonItem2, sizeSearch2);
            }

            private void initClass() {
                this.addClassName("price_block_style");
                enterSize.addClassName("enter_size_style");
                lastSale.addClassName("last_sale_style");
                lowestAsk.addClassName("lowest_ask_style");
                highestBid.addClassName("highest_bid_style");
                sale.addClassName("span_style_1");
                lastSaleString.addClassName("last_sale_string_style");
                differentMoney.addClassName("span_style_2");
                differentPercent.addClassName("span_style_2");
                priceLastSale.addClassName("span_style_3");
                underlastSale.addClassName("under_last_style");
                buttonItem1.addClassName("button_item1_style");
                buttonItem2.addClassName("button_item2_style");
                sizeSearch1.addClassName("size_search1_style");
                sizeSearch2.addClassName("size_search2_style");
            }
        }
}
