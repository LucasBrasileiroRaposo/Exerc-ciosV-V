describe("options", () => {
  beforeEach(() => {
    cy.visit("/admin");
    cy.get('[id="_username"]').type("sylius");
    cy.get('[id="_password"]').type("sylius");
    cy.get(".primary").click();
  });
  // Remove .only and implement others test cases!
  it("edit size XL to GG in Portuguese (Portugal)", () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');
    // Type in value input to search for specify option
    cy.get('[id="criteria_search_value"]').type("t_shirt_size");
    // Click in filter blue button
    cy.get('*[class^="ui blue labeled icon button"]').click();
    // Click in edit of the remain option
    cy.get('*[class^="ui labeled icon button "]').last().click();
    // Edit options values for XL size to GG
    cy.get('[id="sylius_product_option_values_3_translations_pt_PT_value"]')
      .scrollIntoView()
      .clear()
      .type("GG");
    // Click on Save changes button
    cy.get('[id="sylius_save_changes_button"]').scrollIntoView().click();

    // Assert that option has been updated
    cy.get("body").should(
      "contain",
      "Product option has been successfully updated."
    );
  });

  it("create options product", () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Click on create option button
    cy.get('*[class^="ui labeled icon button  primary "]').click();

    // Type the option code
    cy.get('[id="sylius_product_option_code"]').type("teste_1");

    // Type the option position
    cy.get('[id="sylius_product_option_position"]').type(4);

    // Type the option name in US language
    cy.get('[id="sylius_product_option_translations_en_US_name"]').type(
      "teste1"
    );

    // Click on Save changes button
    cy.get('*[class^="ui labeled icon primary button"]').click();

    // Assert that option should be created and sucess message is displayed
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should(
      "exist"
    );
  });

  it("delete options product", () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Find and click in the check-box of the last option of the table
    cy.get("table tr:last-child td:first-child").click();

    // Click in delete button above the table
    cy.get('form[method="post"]').find("button").first().click();

    // Click on confirmation button to delete an option
    cy.get('[id="confirmation-button"]').click();

    // Assert that option should be deleted and sucess message be displayed
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should(
      "exist"
    );
  });

  it("cancel deletion product options", () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');
    let firstChild;

    // Get the info of an option before deletion operation start
    cy.get("table")
      .find("tr")
      .eq(1)
      .find("td")
      .eq(2)
      .then((element) => {
        firstChild = element.text();
      });

    // Find and click in the check-box of the last option of the table
    cy.get("table tr:first-child td:first-child").click();

    // Click in delete button above the table
    cy.get('form[method="post"]').find("button").first().click();

    // Click on cancel button to abort deletion of an option
    cy.get('*[class^="ui red basic cancel inverted button"]').click();

    // Assert that an option in the table is the same as she was before deletion operation started
    cy.get("table")
      .find("tr")
      .eq(1)
      .find("td")
      .eq(2)
      .should((element) => {
        expect(element.text()).to.eq(firstChild); // Comparação do estado atual com o estado inicial
      });
    cy.get("table tr:first-child td:first-child").click();
  });

  it(`edit product's options name in US language`, () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Find and click in the edit button of the last option of the table
    cy.get("table tr:first-child td:last-child").find("a").click();

    // Find the input, clear the old value and type a new one
    cy.get('[id="sylius_product_option_translations_en_US_name"]')
      .clear()
      .type("changed");

    // Click on Save changes button
    cy.get('[id="sylius_save_changes_button"]').click();

    // Assert that option should be updated and sucess message is displayed
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should(
      "exist"
    );
  });

  it(`edit product's options name in PT language`, () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Find and click in the edit button of the first option of the table
    cy.get("table tr:last-child td:last-child").find("a").click();

    // Click in the field to change the name of the product in PT
    cy.get('div[data-locale="pt_PT"]').click();

    // Find the input, clear the old value and type a new one
    cy.get('[id="sylius_product_option_translations_pt_PT_name"]')
      .clear()
      .type("calça jeans");

    // Click on Save changes button
    cy.get('[id="sylius_save_changes_button"]').click();

    // Assert that option should be updated and sucess message is displayed
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should(
      "exist"
    );
  });

  it(`filter options by containing dress word`, () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Find and fill the input with filter word
    cy.get('[id="criteria_search_value"]').type("dress");

    // Click in filter blue button to apply the filter
    cy.get('*[class^="ui blue labeled icon button"]').click();

    // Assert that the table shows only 2 options after applying the filter
    cy.get("table tbody tr").should("have.length", 2);
  });

  it(`filter options by containing changed word`, () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Find and fill the input with filter word
    cy.get("select").select("equal");

    // Click in filter blue button to apply the filter
    cy.get('[id="criteria_search_value"]').type("changed");

    // Click in filter blue button to apply the filter
    cy.get('*[class^="ui blue labeled icon button"]').click();

    // Assert that the table shows only 1 options after applying the filter
    cy.get("table tbody tr").should("have.length", 1);
  });

  it("create product options with values ", () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Click on create option button
    cy.get('*[class^="ui labeled icon button  primary "]').click();

    // Type the option code
    cy.get('[id="sylius_product_option_code"]').type("test_option");

    // Type the option position
    cy.get('[id="sylius_product_option_position"]').type(4);

    // Type the option name in US language
    cy.get('[id="sylius_product_option_translations_en_US_name"]').type(
      "test_option_size_s"
    );

    // Click in add values button
    cy.clickInFirst('a[href="#"]');

    // Fill values for each language
    cy.get('[id="sylius_product_option_values_0_code"]').type("test_option");
    cy.get(
      '[id="sylius_product_option_values_0_translations_en_US_value"]'
    ).type("s");
    cy.get(
      '[id="sylius_product_option_values_0_translations_de_DE_value"]'
    ).type("s");
    cy.get(
      '[id="sylius_product_option_values_0_translations_fr_FR_value"]'
    ).type("s");
    cy.get(
      '[id="sylius_product_option_values_0_translations_pl_PL_value"]'
    ).type("s");
    cy.get(
      '[id="sylius_product_option_values_0_translations_es_ES_value"]'
    ).type("s");
    cy.get(
      '[id="sylius_product_option_values_0_translations_es_MX_value"]'
    ).type("s");
    cy.get(
      '[id="sylius_product_option_values_0_translations_pt_PT_value"]'
    ).type("s");
    cy.get(
      '[id="sylius_product_option_values_0_translations_zh_CN_value"]'
    ).type("s");

    // Click on Save changes button
    cy.get('*[class^="ui labeled icon primary button"]').click();

    // Assert that option should be created and sucess message is displayed
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should(
      "exist"
    );
  });

  it(`edit product's options value`, () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Find and click in the edit button of the last option of the table
    cy.get("table tr:last-child td:last-child").find("a").click();

    // Clear old values and fill new ones for each language
    cy.get('[id="sylius_product_option_values_0_translations_en_US_value"]')
      .clear()
      .type("p");
    cy.get('[id="sylius_product_option_values_0_translations_de_DE_value"]')
      .clear()
      .type("p");
    cy.get('[id="sylius_product_option_values_0_translations_fr_FR_value"]')
      .clear()
      .type("p");
    cy.get('[id="sylius_product_option_values_0_translations_pl_PL_value"]')
      .clear()
      .type("p");
    cy.get('[id="sylius_product_option_values_0_translations_es_ES_value"]')
      .clear()
      .type("p");
    cy.get('[id="sylius_product_option_values_0_translations_es_MX_value"]')
      .clear()
      .type("p");
    cy.get('[id="sylius_product_option_values_0_translations_pt_PT_value"]')
      .clear()
      .type("p");
    cy.get('[id="sylius_product_option_values_0_translations_zh_CN_value"]')
      .clear()
      .type("p");

    // Click on Save changes button
    cy.get('[id="sylius_save_changes_button"]').click();

    // Assert that option should be updated and sucess message is displayed
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should(
      "exist"
    );
  });
  it("delete teste_option product", () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');

    // Find and click in the check-box of the last option of the table
    cy.get("table tr:last-child td:first-child").click();

    // Click in delete button above the table
    cy.get('form[method="post"]').find("button").first().click();

    // Click on confirmation button to delete an option
    cy.get('[id="confirmation-button"]').click();

    // Assert that option should be deleted and sucess message be displayed
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should(
      "exist"
    );
  });
});
