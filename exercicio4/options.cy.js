describe('options', () => {
  beforeEach(() => {
    cy.visit('/admin');
    cy.get('[id="_username"]').type('sylius');
    cy.get('[id="_password"]').type('sylius');
    cy.get('.primary').click();
  });
  // Remove .only and implement others test cases!
  it('edit size XL to GG in Portuguese (Portugal)', () => {
    // Click in options in side menu
    cy.clickInFirst('a[href="/admin/product-options/"]');
    // Type in value input to search for specify option
    cy.get('[id="criteria_search_value"]').type('jeans_size');
    // Click in filter blue button
    cy.get('*[class^="ui blue labeled icon button"]').click();
    // Click in edit of the remain option
    cy.get('*[class^="ui labeled icon button "]').last().click();
    // Edit options values for XL size to GG
    cy.get('[id="sylius_product_option_values_3_translations_pt_PT_value"]').scrollIntoView().clear().type('GG');
    // Click on Save changes button
    cy.get('[id="sylius_save_changes_button"]').scrollIntoView().click();

    // Assert that option has been updated
    cy.get('body').should('contain', 'Product option has been successfully updated.');
  });
  it('create options product', () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    cy.get('*[class^="ui labeled icon button  primary "]').click();
    cy.get('[id="sylius_product_option_code"]').type('teste_1');
    cy.get('[id="sylius_product_option_position"]').type(4);
    cy.get('[id="sylius_product_option_translations_en_US_name"]').type('teste1');
    cy.get('*[class^="ui labeled icon primary button"]').click();
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should('exist');
  });
  it('delete options product', () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    cy.get('table tr:last-child td:first-child').click();
    cy.get('form[method="post"]').find('button').first().click();
    cy.get('[id="confirmation-button"]').click();
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should('exist');
  });
  it('cancel delete product options', () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    let firstChild;

    cy.get('table')
      .find('tr')
      .eq(1)
      .find('td')
      .eq(2)
      .then((element) => {
        firstChild = element.text();
      });

    cy.get('table tr:first-child td:first-child').click();
    cy.get('form[method="post"]').find('button').first().click();
    cy.get('*[class^="ui red basic cancel inverted button"]').click();
    cy.get('table')
      .find('tr')
      .eq(1)
      .find('td')
      .eq(2)
      .should((element) => {
        expect(element.text()).to.eq(firstChild); // Comparação do estado atual com o estado inicial
      });
    cy.get('table tr:first-child td:first-child').click();
  });
  it(`edit product's options name in US language`, () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    cy.get('table tr:last-child td:last-child').find('a').click();
    cy.get('[id="sylius_product_option_translations_en_US_name"]').clear().type('mudei');
    cy.get('[id="sylius_save_changes_button"]').click();
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should('exist');
  });
  it(`edit product's options name in PT language`, () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    cy.get('table tr:last-child td:last-child').find('a').click();
    cy.get('div[data-locale="pt_PT"]').click();
    cy.get('[id="sylius_product_option_translations_pt_PT_name"]').clear().type('mudei');
    cy.get('[id="sylius_save_changes_button"]').click();
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should('exist');
  });
  it(`filter by contains dress`, () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    cy.get('[id="criteria_search_value"]').type('dress');
    cy.get('*[class^="ui blue labeled icon button"]').click();
    cy.get('table tbody tr').should('have.length', 2);
  });
  it(`filter by equal mudei`, () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    cy.get('select').select('equal');
    cy.get('[id="criteria_search_value"]').type('mudei');
    cy.get('*[class^="ui blue labeled icon button"]').click();
    cy.get('table tbody tr').should('have.length', 1);
  });
  it('create product options with values ', () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    cy.get('*[class^="ui labeled icon button  primary "]').click();
    cy.get('[id="sylius_product_option_code"]').type('teste_2');
    cy.get('[id="sylius_product_option_position"]').type(4);
    cy.get('[id="sylius_product_option_translations_en_US_name"]').type('teste2');
    cy.clickInFirst('a[href="#"]');
    cy.get('[id="sylius_product_option_values_0_code"]').type('teste2_size_s');
    cy.get('[id="sylius_product_option_values_0_translations_en_US_value"]').type('s');
    cy.get('[id="sylius_product_option_values_0_translations_de_DE_value"]').type('s');
    cy.get('[id="sylius_product_option_values_0_translations_fr_FR_value"]').type('s');
    cy.get('[id="sylius_product_option_values_0_translations_pl_PL_value"]').type('s');
    cy.get('[id="sylius_product_option_values_0_translations_es_ES_value"]').type('s');
    cy.get('[id="sylius_product_option_values_0_translations_es_MX_value"]').type('s');
    cy.get('[id="sylius_product_option_values_0_translations_pt_PT_value"]').type('s');
    cy.get('[id="sylius_product_option_values_0_translations_zh_CN_value"]').type('s');
    cy.get('*[class^="ui labeled icon primary button"]').click();
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should('exist');
  });
  it(`edit product's options value`, () => {
    cy.clickInFirst('a[href="/admin/product-options/"]');
    cy.get('table tr:last-child td:last-child').find('a').click();
    cy.get('[id="sylius_product_option_values_0_translations_en_US_value"]').clear().type('p');
    cy.get('[id="sylius_product_option_values_0_translations_de_DE_value"]').clear().type('p');
    cy.get('[id="sylius_product_option_values_0_translations_fr_FR_value"]').clear().type('p');
    cy.get('[id="sylius_product_option_values_0_translations_pl_PL_value"]').clear().type('p');
    cy.get('[id="sylius_product_option_values_0_translations_es_ES_value"]').clear().type('p');
    cy.get('[id="sylius_product_option_values_0_translations_es_MX_value"]').clear().type('p');
    cy.get('[id="sylius_product_option_values_0_translations_pt_PT_value"]').clear().type('p');
    cy.get('[id="sylius_product_option_values_0_translations_zh_CN_value"]').clear().type('p');
    cy.get('[id="sylius_save_changes_button"]').click();
    cy.get('*[class^="ui icon positive message sylius-flash-message"]').should('exist');
  });
  // Implement the remaining test cases in a similar manner
});
