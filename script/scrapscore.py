import json
import os

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions

def pull_pages(url):
    pages = []
    with webdriver.Firefox(executable_path=os.path.abspath("lib/geckodriver")) as driver:
        driver.get(url)
        wait = WebDriverWait(driver, 60)

        def wait_for_pagination():
            print("Waiting to load page...")
            wait.until(expected_conditions.presence_of_element_located((By.CSS_SELECTOR, ".ranking-table__row-cell__rank")))

        def click_next_page():
            old_entry = driver.find_element_by_css_selector(".ranking-table__row-cell__rank").text
            driver.find_element(By.CSS_SELECTOR, ".ranking-table-pagination-chevron-right").click()
            wait.until(lambda d: d.find_element_by_css_selector(".ranking-table__row-cell__rank").text != old_entry)
            return read_current_page()

        def read_current_page():
            return int(driver.find_element(By.CSS_SELECTOR, ".ranking-table-page-number-input input").get_attribute("value"))

        def read_total_pages():
            return int(driver.find_element(By.CSS_SELECTOR, ".ranking-table-page-number-total-pages").text.split(" ")[1])

        wait_for_pagination()

        i = read_current_page()
        total = read_total_pages()
        while i < total:
            pages.append(driver.page_source)
            i = click_next_page()
    return pages


if __name__ == "__main__":
    sites = {
        # ("R0", "A"): "https://codingcompetitions.withgoogle.com/codejam/round/00000000002017f7",
        # ("R1", "A"): "https://codingcompetitions.withgoogle.com/codejam/round/0000000000201843",
        # ("R1", "B"): "https://codingcompetitions.withgoogle.com/codejam/round/000000000020187f",
        # ("R1", "C"): "https://codingcompetitions.withgoogle.com/codejam/round/0000000000201842",
        ("R2", "A"): "https://codingcompetitions.withgoogle.com/codejam/round/0000000000201900",
        ("R3", "A"): "https://codingcompetitions.withgoogle.com/codejam/round/0000000000201902",
        ("R4", "A"): "https://codingcompetitions.withgoogle.com/codejam/round/0000000000201909"
    }

    for key, url in sites.items():
        pages = pull_pages(url)
        with open(f"data/gcj2017-pages-{'-'.join(key)}.json", "w") as f:
            json.dump(pages, f)
