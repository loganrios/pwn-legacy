import React from "react";
import WorkScreenInfo from "./WorkScreenInfo";

export default {
  title: "WorkScreenInfo",
  component: WorkScreenInfo,
  argTypes: {
    onEdit: { action: "Don't be afraid of change" },
    onGoToPage: { action: "Don't you want to read me?" },
    onEditCover: { action: "Change is inevitable." },
  },
};

const Template = (args) => <WorkScreenInfo {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  workTitle: "Of WebNovels and Sleep Deprivation",
  author: "Taz",
  blurb:
    "A riveting, action packed story about a handful of sleep deprived software developers desperately fighting to finish their WebNovel project in time. Hair is lost, basic understanding of why things work is questioned, and excessive amounts of caffeine are consumed. Will they succeed? Find out in this 973 chapter installment of their epic saga.",
  image:
    "https://m.media-amazon.com/images/P/B09QNKYWZF.01._SCLZZZZZZZ_SX500_.jpg",
  avgPostTime: "7 Days",
  avgChapterLength: "2500 Words",
  totalViews: 40866,
  averageViews: 42,
  followers: 27,
  publicRatings: 12,
  pages: 8845,
  genres: [
    {
      value: "0",
      label: "Non-Fiction",
      onTagFilter: () => console.log("Filtering to show Non-Fiction Works"),
    },
    {
      value: "1",
      label: "Fan-Fiction",
      onTagFilter: () => console.log("Filtering to show Fan-Fiction Works"),
    },
  ],
  tags: [
    {
      value: "0",
      label: "Slice of Life",
      onTagFilter: () => console.log("Filtering to show Slice of Life Works"),
    },
    {
      value: "1",
      label: "Thriller",
      onTagFilter: () => console.log("Filtering to show Thriller Works"),
    },
    {
      value: "2",
      label: "Existential Crisis",
      onTagFilter: () =>
        console.log("Filtering to show Existential Crisis Works"),
    },
  ],
  warnings: [
    {
      value: "0",
      label: "Gore",
      onTagFilter: () => console.log("Filtering to show Gore Works"),
    },
    {
      value: "1",
      label: "18+",
      onTagFilter: () => console.log("Filtering to show 18+ Works"),
    },
  ],
};
