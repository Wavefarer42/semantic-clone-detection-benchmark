import bs4
import pandas as pd
import json

files = {
    ("R0", "A"): "data/gcj2017-pages-R0-A.json",
    ("R1", "A"): "data/gcj2017-pages-R1-A.json",
    ("R1", "B"): "data/gcj2017-pages-R1-B.json",
    ("R1", "C"): "data/gcj2017-pages-R1-C.json",
    ("R2", "A"): "data/gcj2017-pages-R2-A.json",
    ("R3", "A"): "data/gcj2017-pages-R3-A.json",
    ("R4", "A"): "data/gcj2017-pages-R4-A.json"
}

dfs = []
for (round, category), file in files.items():
    print(f"Handling {round, category}")
    with open(file, "r") as f:
        pages = json.load(f)

    for i, page in enumerate(pages):
        print(f"[{round, category}] Page {i}")

        soup = bs4.BeautifulSoup(page, "html.parser")

        scoreboard = list(soup.find(id="scoreboard").find_all(class_="section-row-pane"))
        names = [it.text for it in scoreboard[0].find_all("p")[1:]]
        scores = [int(it.text) for it in scoreboard[2].find_all("span", class_="user-total-score")]

        dfs.append(pd.DataFrame({"Developer": names,
                                 "Score": scores,
                                 "Round": [round] * len(names),
                                 "Category": [category] * len(names)}))

print(f"Concatinating")
df_scores = pd.concat(dfs, axis=0).set_index(["Developer", "Round", "Category"])
df_source = pd.read_csv("data/2017-gcj-source.csv.gz").set_index(["Developer", "Round", "Category"])

df_new = df_source.join(df_scores, how="left").reset_index()
df_new.to_csv("data/2017-gcj-source.csv.gz", index=False)