const { Builder, By, until, Keys } = require("selenium-webdriver");
const assert = require("assert");

describe("options", () => {
  let driver;

  before(async () => {
    driver = await new Builder().forBrowser("firefox").build();
  });

  after(async () => {
    await driver.quit();
  });

  beforeEach(async () => {
    driver.manage().deleteAllCookies();
    await driver.get("http://localhost:8080/admin");
    // await driver.get('http://150.165.75.99:8080/admin');
    await driver.findElement(By.id("_username")).sendKeys("sylius");
    await driver.findElement(By.id("_password")).sendKeys("sylius");
    await driver.findElement(By.css(".primary")).click();
    // await driver.sleep(1000);
  });

  // Remove .only and implement others test cases!
  it("edit size XL to GG in Portuguese (Portugal)", async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Type in value input to search for specify option
    await driver
      .findElement(By.id("criteria_search_value"))
      .sendKeys("jeans_size");

    // Click in filter blue button
    await driver
      .findElement(By.css('*[class^="ui blue labeled icon button"]'))
      .click();

    // Click in edit of the remain option
    const buttons = await driver.findElements(
      By.css('*[class^="ui labeled icon button "]')
    );
    await buttons[1].click();

    // Edit options values for XL size to GG
    const inputName = await driver.findElement(
      By.id("sylius_product_option_values_3_translations_pt_PT_value")
    );
    inputName.click();
    inputName.clear();
    inputName.sendKeys("GG");

    // Click on Save changes button
    await driver.findElement(By.id("sylius_save_changes_button")).click();

    // Assert that option has been updated
    const bodyText = await driver.findElement(By.tagName("body")).getText();
    assert(bodyText.includes("Product option has been successfully updated."));
  });

  it("create options product", async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Click on create option button
    await driver
      .findElement(By.css('*[class^="ui labeled icon button  primary "]'))
      .click();

    // Type the option code
    await driver
      .findElement(By.id("sylius_product_option_code"))
      .sendKeys("teste_2");

    // Type the option position
    await driver
      .findElement(By.id("sylius_product_option_position"))
      .sendKeys(4);

    // Type the option name in US language
    await driver
      .findElement(By.id("sylius_product_option_translations_en_US_name"))
      .sendKeys("teste2");

    // Click on Save changes button
    await driver
      .findElement(By.css('*[class^="ui labeled icon primary button"]'))
      .click();

    // Assert that option has been created and sucess message is displayed
    const bodyText = await driver
      .findElement(
        By.css('*[class^="ui icon positive message sylius-flash-message"]')
      )
      .getText();
    assert(bodyText.includes("Product option has been successfully created."));
  });

  it("delete options product", async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Find and click in the check-box of the last option of the table
    const tabela = await driver.findElements(By.tagName("tr"));
    const ultimo_filho = tabela[tabela.length - 1];
    const colunas = await ultimo_filho.findElements(By.tagName("td"));
    await colunas[0].findElement(By.tagName("input")).click();

    // Click in delete button above the table
    await driver
      .findElement(By.css('form[method="post"]'))
      .findElement(By.tagName("button"))
      .click();

    // Click on confirmation button to delete an option
    await driver.findElement(By.id("confirmation-button")).click();

    // Assert that option has been deleted and sucess message is displayed
    const bodyText = await driver
      .findElement(
        By.css('*[class^="ui icon positive message sylius-flash-message"]')
      )
      .getText();
    assert(
      bodyText.includes("Product_options have been successfully deleted.")
    );
  });

  it("cancel product delete product option", async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();
    // Find and click in the check-box of the last option of the table
    const tabela = await driver.findElements(By.tagName("tr"));
    const ultimo_filho = tabela[tabela.length - 1];
    const colunas = await ultimo_filho.findElements(By.tagName("td"));
    await colunas[0].findElement(By.tagName("input")).click();
    // Click in delete button above the table
    await driver
      .findElement(By.css('form[method="post"]'))
      .findElement(By.tagName("button"))
      .click();
    // Click on cancel button
    await driver
      .findElement(By.css('*[class^="ui red basic cancel inverted button"]'))
      .click();
    assert(tabela.length === 5);
  });

  it(`edit product's options name in US language`, async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Find and click in the edit button of the last option of the table
    const tabela = await driver.findElements(By.tagName("tr"));
    const ultimo_filho = tabela[tabela.length - 1];
    const colunas = await ultimo_filho.findElements(By.tagName("td"));
    await colunas[colunas.length - 1].findElement(By.tagName("a")).click();

    // Find the input, clear the old value and type a new one
    await driver
      .findElement(By.id("sylius_product_option_translations_en_US_name"))
      .clear();
    await driver
      .findElement(By.id("sylius_product_option_translations_en_US_name"))
      .sendKeys("changed");

    // Click on Save changes button
    await driver.findElement(By.id("sylius_save_changes_button")).click();

    // Assert that option has been updated by sucess message is displayed
    const bodyText = await driver
      .findElement(
        By.css('*[class^="ui icon positive message sylius-flash-message"]')
      )
      .getText();
    assert(bodyText.includes("Product option has been successfully updated."));
  });
  it(`edit product's options name in PT language`, async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Find and click in the edit button of the first option of the table
    const tabela = await driver.findElements(By.tagName("tr"));
    const primeiro_filho = tabela[1];
    const colunas = await primeiro_filho.findElements(By.tagName("td"));
    await colunas[colunas.length - 1].findElement(By.tagName("a")).click();

    // Click in the field to change the name of the product in PT
    await driver.findElement(By.css('div[data-locale="pt_PT"]')).click();

    // Find the input, clear the old value and type a new one
    await driver
      .findElement(By.id("sylius_product_option_translations_pt_PT_name"))
      .clear();
    await driver
      .findElement(By.id("sylius_product_option_translations_pt_PT_name"))
      .sendKeys("calça jeans");

    // Click on Save changes button
    await driver.findElement(By.id("sylius_save_changes_button")).click();

    // Assert that option has been updated by sucess message is displayed
    const bodyText = await driver
      .findElement(
        By.css('*[class^="ui icon positive message sylius-flash-message"]')
      )
      .getText();
    assert(bodyText.includes("Product option has been successfully updated."));
  });
  it(`filter options by containing dress word`, async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Find and fill the input with filter word
    await driver.findElement(By.id("criteria_search_value")).sendKeys("dress");

    // Click in filter blue button to apply the filter
    await driver
      .findElement(By.css('*[class^="ui blue labeled icon button"]'))
      .click();

    // Find and save all the options present in the table on a variable
    const tabela = await driver.findElements(By.tagName("tr"));

    // Assert that the table shows only 3 options after applying the filter
    assert(tabela.length === 3);
  });
  it(`filter options by containing changed word`, async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Find and fill the input with filter word
    await driver
      .findElement(By.id("criteria_search_value"))
      .sendKeys("changed");

    // Click in filter blue button to apply the filter
    await driver
      .findElement(By.css('*[class^="ui blue labeled icon button"]'))
      .click();

    // Find and save all the options present in the table on a variable
    const tabela = await driver.findElements(By.tagName("tr"));

    // Assert that the table shows only 2 options after applying the filter
    assert(tabela.length === 2);
  });

  it(`create product options with values in each language`, async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Click on create option button
    await driver
      .findElement(By.css('*[class^="ui labeled icon button  primary "]'))
      .click();

    // Type the option code
    await driver
      .findElement(By.id("sylius_product_option_code"))
      .sendKeys("test_option");

    // Type the option position
    await driver
      .findElement(By.id("sylius_product_option_position"))
      .sendKeys(4);

    // Type the option name in US language
    await driver
      .findElement(By.id("sylius_product_option_translations_en_US_name"))
      .sendKeys("test_option");

    // Click in add values button
    await driver.findElement(By.css('a[href="#"]')).click();

    // Fill values for each language
    await driver
      .findElement(By.id("sylius_product_option_values_0_code"))
      .sendKeys("test_size_s");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_us_US_value")
      )
      .sendKeys("s");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_de_DE_value")
      )
      .sendKeys("s");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_fr_FR_value")
      )
      .sendKeys("s");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_pl_PL_value")
      )
      .sendKeys("s");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_es_ES_value")
      )
      .sendKeys("s");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_es_MX_value")
      )
      .sendKeys("s");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_pt_PT_value")
      )
      .sendKeys("s");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_zh_CN_value")
      )
      .sendKeys("s");

    // Click on Save changes button
    await driver
      .findElement(By.css('*[class^="ui labeled icon primary button"]'))
      .click();

    // Assert that option has been created and sucess message is displayed
    const bodyText = await driver
      .findElement(
        By.css('*[class^="ui icon positive message sylius-flash-message"]')
      )
      .getText();
    assert(bodyText.includes("Product option has been successfully created."));
  });

  it(`edit all product's options value`, async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText("Options")).click();

    // Find and click in the edit button of the last option of the table
    const tabela = await driver.findElements(By.tagName("tr"));
    const ultimo_filho = tabela[tabela.length - 1];
    const colunas = await ultimo_filho.findElements(By.tagName("td"));
    await colunas[colunas.length - 1].findElement(By.tagName("a")).click();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_en_US_value")
      )
      .clear();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_en_US_value")
      )
      .sendKeys("p");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_de_DE_value")
      )
      .clear();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_de_DE_value")
      )
      .sendKeys("p");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_fr_FR_value")
      )
      .clear();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_fr_FR_value")
      )
      .sendKeys("p");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_pl_PL_value")
      )
      .clear();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_pl_PL_value")
      )
      .sendKeys("p");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_es_ES_value")
      )
      .clear();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_es_ES_value")
      )
      .sendKeys("p");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_es_MX_value")
      )
      .clear();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_es_MX_value")
      )
      .sendKeys("p");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_pt_PT_value")
      )
      .clear();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_pt_PT_value")
      )
      .sendKeys("p");
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_zh_CN_value")
      )
      .clear();
    await driver
      .findElement(
        By.id("sylius_product_option_values_0_translations_zh_CN_value")
      )
      .sendKeys("p");
    await driver
      .findElement(By.css('[id="sylius_save_changes_button"]'))
      .click();
  });
  // Implement the remaining test cases in a similar manner
});
