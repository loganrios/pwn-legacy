import React from "react";
import WorkDashboardInfo from "./WorkDashboardInfo";

export default {
  title: "WorkDashboardInfo",
  component: WorkDashboardInfo,
  argTypes: {
    onEdit: { action: "Don't be afraid of change" },
    onGoToPage: { action: "Don't you want to read me?" },
    onEditCover: { action: "Change is inevitable." },
  },
};

const Template = (args) => <WorkDashboardInfo {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  workTitle: "Of WebNovels and Sleep Deprivation",
  blurb:
    "A riveting, action packed story about a handful of sleep deprived software developers desperately fighting to finish their WebNovel project in time. Hair is lost, basic understanding of why things work is questioned, and excessive amounts of caffeine are consumed. Will they succeed? Find out in this 973 chapter installment of their epic saga.",
  image:
    "https://m.media-amazon.com/images/P/B09QNKYWZF.01._SCLZZZZZZZ_SX500_.jpg",
};
