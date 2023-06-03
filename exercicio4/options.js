const { Builder, By, until, Keys } = require('selenium-webdriver');
const assert = require('assert');

describe('options', () => {
  let driver;

  before(async () => {
    driver = await new Builder().forBrowser('firefox').build();
  });

  after(async () => {
    await driver.quit();
  });

  beforeEach(async () => {
    driver.manage().deleteAllCookies();
    await driver.get('http://localhost:8080/admin');
    // await driver.get('http://150.165.75.99:8080/admin');
    await driver.findElement(By.id('_username')).sendKeys('sylius');
    await driver.findElement(By.id('_password')).sendKeys('sylius');
    await driver.findElement(By.css('.primary')).click();
    // await driver.sleep(1000);
  });

  // Remove .only and implement others test cases!
  it('edit size XL to GG in Portuguese (Portugal)', async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText('Options')).click();

    // Type in value input to search for specify option
    await driver.findElement(By.id('criteria_search_value')).sendKeys('jeans_size');

    // Click in filter blue button
    await driver.findElement(By.css('*[class^="ui blue labeled icon button"]')).click();

    // Click in edit of the remain option
    const buttons = await driver.findElements(By.css('*[class^="ui labeled icon button "]'));
    await buttons[1].click();

    // Edit options values for XL size to GG
    const inputName = await driver.findElement(By.id('sylius_product_option_values_3_translations_pt_PT_value'));
    inputName.click();
    inputName.clear();
    inputName.sendKeys('GG');

    // Click on Save changes button
    await driver.findElement(By.id('sylius_save_changes_button')).click();

    // Assert that option has been updated
    const bodyText = await driver.findElement(By.tagName('body')).getText();
    assert(bodyText.includes('Product option has been successfully updated.'));
  });

  it('create options product', async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText('Options')).click();

    // Type click on create option button
    await driver.findElement(By.css('*[class^="ui labeled icon button  primary "]')).click();

    // Type the option code
    await driver.findElement(By.id('sylius_product_option_code')).sendKeys('teste_2');

    // Type the option position
    await driver.findElement(By.id('sylius_product_option_position')).sendKeys(4);

    // Type the option name in US language
    await driver.findElement(By.id('sylius_product_option_translations_en_US_name')).sendKeys('teste2');

    // Click on Save changes button
    await driver.findElement(By.css('*[class^="ui labeled icon primary button"]')).click();

    // Assert that option has been created and sucess message is displayed
    const bodyText = await driver.findElement(By.css('*[class^="ui icon positive message sylius-flash-message"]')).getText();
    assert(bodyText.includes('Product option has been successfully created.'));
  });

  it('delete options product', async () => {
    // Click in options in side menu
    await driver.findElement(By.linkText('Options')).click();

    const tabela = await driver.findElements(By.tagName('tr'));
    const ultimo_filho = tabela[tabela.length - 1];
    const colunas = await ultimo_filho.findElements(By.tagName('td'));
    await colunas[0].findElement(By.tagName('input')).click();
    await driver.findElement(By.css('form[method="post"]')).findElement(By.tagName('button')).click();
    await driver.findElement(By.id('confirmation-button')).click();
    const bodyText = await driver.findElement(By.css('*[class^="ui icon positive message sylius-flash-message"]')).getText();
    assert(bodyText.includes('Product_options have been successfully deleted.'));
  });

  it('cancel product delete product option', async () => {
    await driver.findElement(By.linkText('Options')).click();
  });

  it(`edit product's options name in US language`, async () => {
    await driver.findElement(By.linkText('Options')).click();
    const tabela = await driver.findElements(By.tagName('tr'));
    const ultimo_filho = tabela[tabela.length - 1];
    const colunas = await ultimo_filho.findElements(By.tagName('td'));
    await colunas[colunas.length - 1].findElement(By.tagName('a')).click();

    await driver.findElement(By.id('sylius_product_option_translations_en_US_name')).clear();
    await driver.findElement(By.id('sylius_product_option_translations_en_US_name')).sendKeys('change');
    await driver.findElement(By.id('sylius_save_changes_button')).click();
    const bodyText = await driver.findElement(By.css('*[class^="ui icon positive message sylius-flash-message"]')).getText();
    assert(bodyText.includes('Product option has been successfully updated.'));
  });
  it(`edit product's options name in PT language`, async () => {
    await driver.findElement(By.linkText('Options')).click();
    const tabela = await driver.findElements(By.tagName('tr'));
    const ultimo_filho = tabela[tabela.length - 1];
    const colunas = await ultimo_filho.findElements(By.tagName('td'));
    await colunas[colunas.length - 1].findElement(By.tagName('a')).click();
    await driver.findElement(By.css('div[data-locale="pt_PT"]')).click();
    await driver.findElement(By.id('sylius_product_option_translations_pt_PT_name')).clear();
    await driver.findElement(By.id('sylius_product_option_translations_pt_PT_name')).sendKeys('calça jeans');
    await driver.findElement(By.id('sylius_save_changes_button')).click();
    const bodyText = await driver.findElement(By.css('*[class^="ui icon positive message sylius-flash-message"]')).getText();
    assert(bodyText.includes('Product option has been successfully updated.'));
  });
  it(`filter by contains dress`, async () => {
    await driver.findElement(By.linkText('Options')).click();
    await driver.findElement(By.id('criteria_search_value')).sendKeys('dress');
    await driver.findElement(By.css('*[class^="ui blue labeled icon button"]')).click();
    const tabela = await driver.findElements(By.tagName('tr'));

    assert(tabela.length === 3);
  });
  it(`filter by contains mudei`, async () => {
    await driver.findElement(By.linkText('Options')).click();
    await driver.findElement(By.id('criteria_search_value')).sendKeys('change');
    await driver.findElement(By.css('*[class^="ui blue labeled icon button"]')).click();
    const tabela = await driver.findElements(By.tagName('tr'));

    assert(tabela.length === 2);
  });
  // Implement the remaining test cases in a similar manner
});
