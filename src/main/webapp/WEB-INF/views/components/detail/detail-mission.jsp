<div class="mission card ">
    <div class="card-body">
        <h4 class="card-title">Mission details</h4>
        <div class="dropdown-divider mb-4"></div>
        <div class="mission">
            <div class="form-group">
                <h5 class="card-title">
                    <label for="descriptionMission">Mission description</label>
                </h5>
                <textarea class="form-control rounded-0" id="descriptionMission" name="missionDescription"
                          rows="5"><c:out value="${requestScope.internship.mission.description}"/></textarea>
            </div>
            <div class="form-group">
                <h5 class="card-title">
                    <label for="keywords">Keywords</label>
                </h5>
                <textarea class="form-control rounded-0" id="keywords" name="missionKeywords"
                          rows="3"><c:out value="${requestScope.internship.mission.keyWords}"/></textarea>
            </div>
            <div class="form-group">
                <h5 class="card-title">
                    <label for="skills">Mission skills</label>
                </h5>
                <input id="skills" name="skillsRequired" type="text" class="tagator"
                       data-tagator-show-all-options-on-focus="true"
                       data-tagator-autocomplete="['Angular', 'Kanban', 'Kubernetes', 'C++', 'Docker',
            'JEE', 'PHP', 'MongoDB', 'NodeJS', 'TypeScript', 'Unix', 'UX Design',
            'Shell Script', 'Jenkins', 'Scrum', 'Python', 'Hadoop', '.NET', 'Bash']">
            </div>
        </div>
    </div>
</div>
